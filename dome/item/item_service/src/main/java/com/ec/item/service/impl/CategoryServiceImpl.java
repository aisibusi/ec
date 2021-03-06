package com.ec.item.service.impl;

import com.ec.item.repository.CategoryRepository;
import com.ec.item.service.CategoryService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findByPid(Long pid) {
        List<Category> categoryList = categoryRepository.findByParentIdIs(pid);

        if (CollectionUtils.isEmpty(categoryList)) {
            throw new EcException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;

    }

    @Override
    public List<Category> queryCategoryByIds(List<Long> ids) {
        return categoryRepository.findAllById(ids);

    }

    @Override
    public List<Category> queryAllByCid3(Long id) {
        Category c3 = categoryRepository.getOne(id);
        Category c2 = categoryRepository.getOne(c3.getParentId());
        Category c1 = categoryRepository.getOne(c2.getParentId());
        List<Category> list = Arrays.asList(c1, c2, c3);
        if (CollectionUtils.isEmpty(list)) {
            throw new EcException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
