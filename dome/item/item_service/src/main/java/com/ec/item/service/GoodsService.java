package com.ec.item.service;

import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Spu;

public interface GoodsService {
    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

}
