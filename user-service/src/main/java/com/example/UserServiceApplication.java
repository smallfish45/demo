package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    @Resource
    private final RedisTemplate<String, Object> redisTemplate = null;

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        assert false;
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


}
