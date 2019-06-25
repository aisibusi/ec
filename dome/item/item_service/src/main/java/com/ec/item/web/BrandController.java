package com.ec.item.web;


import com.ec.item.service.BrandService;
import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     *
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> findBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "5") int rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key

    ){
        Page<Brand> rep_p = brandService.findBrandByPage(page, rows, sortBy, desc, key);

        return ResponseEntity.ok(
                new PageResult<>(rep_p.getTotalElements(), rep_p.getTotalPages()
                        , rep_p.getContent()));
    }

    @PostMapping
    public ResponseEntity<Void> createBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        brandService.save(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
