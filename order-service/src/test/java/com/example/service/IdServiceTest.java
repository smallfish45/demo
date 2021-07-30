package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdServiceTest {

    @Resource
    private IdService idService;

    @Test
    void getSnowflakeId() {
        String id = idService.getSnowflakeId();
        System.out.println(id);
    }
}