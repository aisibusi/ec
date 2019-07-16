package com.ec.item.repository;

import com.lh.ec.item.pojo.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface CategoryRepository extends BaseRepository<Category, Long> {

    List<Category> findByParentIdIs(Long parentId);

    @Query(value = "select * from tb_category where id in (select category_id from tb_category_brand where brand_id = ?1)", nativeQuery = true)
    List<Category> queryCategoryByBid(Long bid);
}
