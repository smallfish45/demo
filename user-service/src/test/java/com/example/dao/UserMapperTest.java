package com.example.dao;

import com.example.pojo.User;
import com.example.pojo.UserSqlCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Value("user.home")
    private String user;

    @Test
    public void user() {
        System.out.println(user);
    }


    @Test
    void insertUser() {
        User user = new User();
        long uuid = UUID.randomUUID().getMostSignificantBits();
        user.setUuid(Math.abs(uuid));
        user.setUserName("small");
        user.setUserPassword("123456");
        user.setUserMobile("1234567890");
        user.setUserScore(0);
        user.setUserRegTime(new Date(System.currentTimeMillis()));
        System.out.println(new Date(System.currentTimeMillis()));
        user.setUserMoney(1242414L);

        userMapper.insertUser(user);
    }

    @Test
    void mathTest() {
        long uuid = UUID.randomUUID().getMostSignificantBits();
        System.out.println(Math.abs(uuid));
    }

    @Test
    void selectByExample() {

        UserSqlCondition userSqlCondition = new UserSqlCondition();
        UserSqlCondition.Criteria criteria = userSqlCondition.createCriteria();
        criteria.andUserNameEqualTo("small");

        List<User> users = userMapper.selectByExample(userSqlCondition);
        users.forEach(System.out::println);

    }

    @Test
    void updateByExample() {
        UserSqlCondition userSqlCondition = new UserSqlCondition();
        UserSqlCondition.Criteria criteria = userSqlCondition.createCriteria();
        criteria.andUserNameEqualTo("small");

        List<User> users = userMapper.selectByExample(userSqlCondition);
        users.forEach(System.out::println);

        for (User user : users) {
            user.setUserName("smallfish01");
            userSqlCondition.clear();
            UserSqlCondition.Criteria criteria1 = userSqlCondition.createCriteria();
            criteria1.andUUIDEqualTo(user.getUuid());

            userMapper.updateByExampleSelective(user, userSqlCondition);
        }
    }

    @Test
    void updateByExampleSelective() {
    }
}