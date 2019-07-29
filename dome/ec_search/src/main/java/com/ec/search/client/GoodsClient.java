package com.ec.search.client;

import com.lh.ec.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
