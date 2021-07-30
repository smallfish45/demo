package com.example.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ID-SERVICE")
public interface IdService {

    /**
     * 分布式ID
     * @return
     */
    @GetMapping("/leaf")
    String getSnowflakeId();

}
