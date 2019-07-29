package com.lh.ec.item.api;


import com.lh.ec.item.pojo.SpecGroup;
import com.lh.ec.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("spec")
public interface SpecApi {

    @GetMapping("groups/{cid}")
    List<SpecGroup> findSpecGrpByCId(@PathVariable("cid") Long cid);



    @GetMapping("{cid}")
    List<SpecGroup> findSpecsByCid(@PathVariable("cid") Long cid);

    @GetMapping("params")
    List<SpecParam> querySpecParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    );
}
