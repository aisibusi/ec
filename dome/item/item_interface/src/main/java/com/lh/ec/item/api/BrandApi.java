package com.lh.ec.item.api;

import com.lh.ec.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {

    @GetMapping("{id}")
    Brand queryById(@PathVariable("id") Long id);

    @GetMapping("list")
    List<Brand> queryBrandsByIds(@RequestParam("ids") List<Long> ids);
}
