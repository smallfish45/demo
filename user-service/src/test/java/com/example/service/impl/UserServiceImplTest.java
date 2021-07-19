package com.example.service.impl;

import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Test
    void addUser() {

        User user = new User();
        long uuid = UUID.randomUUID().getMostSignificantBits();
        user.setUuid(Math.abs(uuid));
        user.setUserName("small02");
        user.setUserPassword("123456");
        user.setUserMobile("1234567890");
        user.setUserScore(0);
        user.setUserRegTime(new Date(System.currentTimeMillis()));
        System.out.println(new Date(System.currentTimeMillis()));
        user.setUserMoney(1242414L);

        userService.addUser(user);

    }

    @Test
    void updateUser() {

        User small = userService.getUserByName("small");
        if (small == null) {
            LOGGER.warn("user not exist");
        }

        assert small != null;
        small.setUserName("small-service-update-test");
        userService.updateUser(small);


    }

    @Test
    void getUserByName() {

        User userByName = userService.getUserByName("small-service-update-test");
        LOGGER.info("test getUserByName : {}", userByName);

    }

    @Test
    void getAllUser() {
        List<User> allUser = userService.getAllUser();
        allUser.forEach(System.out::println);
    }
}