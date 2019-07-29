package com.lh.ec.item.api;

import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Sku;
import com.lh.ec.item.pojo.Spu;
import com.lh.ec.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsApi {

        @GetMapping("spu/page")
        PageResult<Spu> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable
        );

        @GetMapping("spu/detail/{spuId}")
        SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

        @GetMapping("sku/list")
        List<Sku> querySkuBySpuId(@RequestParam("id") Long id);

        @GetMapping("sku/list/ids")
        List<Sku> querySkusByIds(@RequestParam("ids") List<Long> ids);


        @GetMapping("spu/{id}")
        Spu querySpuBySpuId(@PathVariable("id") Long spuId);




}
