package com.ec.item.service.impl;

import com.ec.item.repository.BrandRepository;
import com.ec.item.repository.CategoryRepository;
import com.ec.item.service.BrandService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.Brand;
import com.lh.ec.item.pojo.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Brand> findBrandByPage(int page, int rows, String sortBy, boolean desc, String key) {

        Page<Brand> byPage = null;
        try {
            byPage = brandRepository.findAll((root, criteriaQuery, builder) -> {

                    List<Predicate> predicates = new ArrayList<>();
                    if(!StringUtils.isEmpty(key)){

                        if(key.length() == 1)
                            predicates.add(builder.equal(root.get("letter"), key.charAt(0)));
                        else
                            predicates.add(builder.like(root.get("name"), "%" + key + "%"));

                    }
                        return builder.and(predicates
                                .toArray(new Predicate[predicates.size()]));

                }
            , PageRequest.of(page - 1, rows, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC
                    , sortBy)));
        } catch (Exception e) {
            throw new EcException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return byPage;
    }

    @Override
    @Transactional
    public void save(Brand brand, List<Long> cids) {

        try {
            brandRepository.save(brand);
            for(Long cid : cids)
                brandRepository.saveCategoryBrand(cid, brand.getId());
        } catch (Exception e) {
            throw new EcException(ExceptionEnum.FAIL_TO_SAVE_BRAND);
        }
    }

    @Override
    public List<Category> findCategoriesByBid(Long bid) {
        log.debug("The brand id is " + bid);
        List<Category> categories = categoryRepository.queryCategoryByBid(bid);
        if (CollectionUtils.isEmpty(categories)) {
            log.debug("Cannot get the categories");
            throw new EcException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }

        return categories;
    }

    @Override
    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        try {
            //Update Brand information
            brandRepository.save(brand);
            log.debug("Brand has been updated");
            //Update Category information
            brandRepository.deleteCategoryBrandByBid(brand.getId());
            log.debug("Categories has been deleted");
            for(Long cid : cids)
                brandRepository.saveCategoryBrand(cid, brand.getId());
        } catch (Exception e) {
            throw new EcException(ExceptionEnum.FAIL_TO_UPDATE_BRAND);
        }
    }

    @Override
    public Brand queryBrandByBid(Long id) {
//        Brand brand = new Brand();
//        brand.setId(id);
        Brand b1 = brandRepository.getOne(id);
        if (b1 == null) {
            throw new EcException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return b1;
    }

    @Override
    public List<Brand> queryBrandByIds(List<Long> ids) {
        List<Brand> brands = brandRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(brands)) {
            throw new EcException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brands;
    }
}
