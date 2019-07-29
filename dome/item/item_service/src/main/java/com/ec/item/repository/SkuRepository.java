package com.ec.item.repository;


import com.lh.ec.item.pojo.Sku;

import java.util.List;

public interface SkuRepository extends BaseRepository<Sku, Long>{
    List<Sku> findBySpuIdIs(Long spuId);
}
