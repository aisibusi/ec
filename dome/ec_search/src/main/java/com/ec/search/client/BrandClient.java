package com.ec.search.client;

import com.lh.ec.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
