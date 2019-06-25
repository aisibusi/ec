package com.ec.item.repository;

import com.lh.ec.item.pojo.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>{

    @Modifying
    @Query(value = "insert into tb_category_brand (category_id, brand_id) values (?1, ?2)", nativeQuery = true)
    void saveCategoryBrand(Long cid, Long bid);
}
