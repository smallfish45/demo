package com.example.service.impl;

import com.example.service.LeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeafServiceImpl implements LeafService {

    private static final String LEAF_URL = "http://localhost:8080/api/snowflake/get/test";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getSnowflakeId() {
        return restTemplate.getForObject(LeafServiceImpl.LEAF_URL, String.class);
    }

}
