package com.ec.item.service;


import com.lh.ec.item.pojo.Category;

import java.util.List;

public interface CategoryService {


    List<Category> findByPid(Long pid);

    List<Category> queryCategoryByIds(List<Long> ids);

    List<Category> queryAllByCid3(Long id);

}
