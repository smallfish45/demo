package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.pojo.User;
import com.example.pojo.UserExample;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

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
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUUIDEqualTo(user.getUuid())
                    .andUserIdEqualTo(user.getUserId());

            userMapper.updateByExampleSelective(user, userExample);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public User getUserByName(String name) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(name);

        List<User> users = userMapper.selectByExample(userExample);

        if (users != null && users.size() == 1) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public List<User> getAllUser() {

        UserExample userExample = new UserExample();
        userExample.createCriteria();

        return userMapper.selectByExample(userExample);
    }

}
