package com.example.service;

import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void getUserByName() {

        User user = userService.getUserByName("postman01");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println(user);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");

    }
}