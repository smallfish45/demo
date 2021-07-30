package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.pojo.User;
import com.example.pojo.UserSqlCondition;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final String REDIS_KEY_PREFIX = "user:";

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean addUser(User user) {
        try {
            long uuid = UUID.randomUUID().getMostSignificantBits();
            user.setUuid(Math.abs(uuid));
            user.setUserRegTime(new Date(System.currentTimeMillis()));
            User userByName = userService.getUserByName(user.getUserName());
            if (userByName != null) {
                LOGGER.warn("user is exist");
                return false;
            }

            userMapper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        LOGGER.info("{} add success", user.getUserName());
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            UserSqlCondition userSqlCondition = new UserSqlCondition();
            UserSqlCondition.Criteria criteria = userSqlCondition.createCriteria();
            criteria.andUUIDEqualTo(user.getUuid())
                    .andUserIdEqualTo(user.getUserId());

            userMapper.updateByExampleSelective(user, userSqlCondition);

            String redisKey = UserServiceImpl.REDIS_KEY_PREFIX + user.getUserName();
            Boolean hasKey = redisTemplate.hasKey(redisKey);
            if (hasKey != null && hasKey) {
                redisTemplate.delete(redisKey);
                LOGGER.info("delete user from redis : {}", hasKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public User getUserByName(String name) {
        String redisKey = UserServiceImpl.REDIS_KEY_PREFIX + name;
        Boolean hasKey = redisTemplate.hasKey(redisKey);
        if (hasKey != null && hasKey) {
            LOGGER.info("return user from redis : {}", redisKey);
            return (User) redisTemplate.opsForValue().get(redisKey);
        }

        UserSqlCondition userSqlCondition = new UserSqlCondition();
        UserSqlCondition.Criteria criteria = userSqlCondition.createCriteria();
        criteria.andUserNameEqualTo(name);

        List<User> users = userMapper.selectByExample(userSqlCondition);

        if (users != null && users.size() == 1) {
            redisTemplate.opsForValue().set(redisKey, users.get(0));
            LOGGER.info("add user to redis : {}", redisKey);
            return users.get(0);
        }

        return null;
    }

    @Override
    public List<User> getAllUser() {

        UserSqlCondition userSqlCondition = new UserSqlCondition();
        userSqlCondition.createCriteria();

        return userMapper.selectByExample(userSqlCondition);
    }

}
