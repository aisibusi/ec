package com.ec.item.service;

import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Sku;
import com.lh.ec.item.pojo.Spu;
import com.lh.ec.item.pojo.SpuDetail;

import java.util.List;

public interface GoodsService {
    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkuBySpuId(Long id);

    List<Sku> querySkuByIds(List<Long> ids);

//    void deleteGoodsBySpuId(Long spuId);

//    void decreaseStock(List<CartDto> cartDtos);

    Spu querySpuBySpuId(Long spuId);
}

