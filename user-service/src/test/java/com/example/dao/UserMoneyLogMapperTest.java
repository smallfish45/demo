package com.example.dao;

import com.example.pojo.User;
import com.example.pojo.UserSqlCondition;
import com.example.pojo.UserMoneyLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserMoneyLogMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMoneyLogMapper userMoneyLogMapper;

    @Test
    void deleteByExample() {
    }

    @Test
    void insert() {
        UserSqlCondition userSqlCondition = new UserSqlCondition();
        UserSqlCondition.Criteria criteria = userSqlCondition.createCriteria();
        criteria.andUserNameEqualTo("small");

        List<User> users = userMapper.selectByExample(userSqlCondition);
        users.forEach(System.out::println);

        for (User user : users) {
            long uuid = Math.abs(UUID.randomUUID().getMostSignificantBits());

            UserMoneyLog userMoneyLog = new UserMoneyLog();
            userMoneyLog.setUuid(user.getUuid());
            userMoneyLog.setUserId(user.getUserId());
            userMoneyLog.setOrderId(uuid);
            userMoneyLog.setMoneyLogType(1);
            userMoneyLog.setCreateTime(new Date(System.currentTimeMillis()));
            userMoneyLog.setUseMoney(BigDecimal.valueOf(456.45));

            userMoneyLogMapper.insert(userMoneyLog);
        }
    }

    @Test
    void selectByExample() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}