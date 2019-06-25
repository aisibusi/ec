package com.ec.item.repository;

import com.lh.ec.item.pojo.Category;

import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIdIs(Long parentId);
}
