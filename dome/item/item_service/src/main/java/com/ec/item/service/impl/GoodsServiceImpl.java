package com.ec.item.service.impl;

import com.ec.item.repository.SpuRepository;
import com.ec.item.service.GoodsService;
import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Spu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuRepository spuRepository;

    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {

        Page<Spu> spuPage = null;
        try {
            spuPage = spuRepository.findAll((root, criteriaQuery, builder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(key))
                    predicates.add(builder.like(root.get("title"), "%" + key + "%"));
                if(saleable != null)
                    predicates.add(builder.equal(root.get("saleable"), saleable));
                return builder.and(predicates
                        .toArray(new Predicate[predicates.size()]));
            }, PageRequest.of(page - 1, rows));
        } catch (Exception e) {
            log.info("Unexpected exception in [querySpuByPage]. The info is " + e.getMessage());
        }
        return new PageResult<>(spuPage.getTotalElements(), spuPage.getTotalPages()
                , spuPage.getContent());
    }
}
