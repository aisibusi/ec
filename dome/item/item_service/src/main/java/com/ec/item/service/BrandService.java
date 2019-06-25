package com.ec.item.service;

import com.lh.ec.item.pojo.Brand;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BrandService {
    Page<Brand> findBrandByPage(int page, int rows, String sortBy, boolean desc, String key);
    void save(Brand brand, List<Long> cids);
}
