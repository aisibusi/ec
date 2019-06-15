package com.ec.item.service.impl;

import com.ec.item.dao.CategoryDao;
import com.ec.item.service.CategoryService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findByPid(Long pid) {
        List<Category> categoryList = categoryDao.findByParentIdIs(pid);

        if (CollectionUtils.isEmpty(categoryList)) {
            throw new EcException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;

    }

    @Override
    public List<Category> queryCategoryByIds(List<Long> ids) {
        return null;
    }

    @Override
    public List<Category> queryAllByCid3(Long id) {
        return null;
    }
}
