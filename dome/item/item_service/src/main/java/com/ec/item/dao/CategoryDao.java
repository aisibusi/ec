package com.ec.item.dao;

import com.lh.ec.item.pojo.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    List<Category> findByParentIdIs(Long parentId);
}
