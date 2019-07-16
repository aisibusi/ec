package com.ec.item.repository;

import com.lh.ec.item.pojo.Brand;
import com.lh.ec.item.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BrandRepository extends BaseRepository<Brand, Long>{

    @Modifying
    @Query(value = "insert into tb_category_brand (category_id, brand_id) values (?1, ?2)", nativeQuery = true)
    void saveCategoryBrand(Long cid, Long bid);

    @Modifying
    @Query(value = "delete from tb_category_brand where brand_id = ?1", nativeQuery = true)
    void deleteCategoryBrandByBid(Long bid);

}
