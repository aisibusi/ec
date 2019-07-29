package com.ec.search.client;

import com.lh.ec.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
