package com.ec.search.client;


import com.lh.ec.item.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface SpecClient extends SpecApi {
}
