package com.ec.item.web;

import com.ec.item.service.BrandService;
import com.ec.item.service.CategoryService;
import com.lh.ec.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid")Long pid){

        return ResponseEntity.ok(categoryService.findByPid(pid));
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid){
        return ResponseEntity.ok(brandService.findCategoriesByBid(bid));
    }
}
