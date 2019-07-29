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

    /**
     * 根据商品分类Ids查询分类
     * @param ids
     * @return
     */
    @GetMapping("list/ids")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(categoryService.queryCategoryByIds(ids));
    }

    /**
     * 根据cid3查询三级分类
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id) {
        return ResponseEntity.ok(categoryService.queryAllByCid3(id));
    }
}
