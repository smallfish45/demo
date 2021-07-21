package com.example.service.impl;

import com.example.dao.UserMoneyLogMapper;
import com.example.pojo.UserMoneyLog;
import com.example.pojo.UserMoneyLogExample;
import com.example.service.UserMoneyLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserMoneyLogServiceImpl implements UserMoneyLogService {

    private Logger LOGGER = LoggerFactory.getLogger(UserMoneyLogServiceImpl.class);

    @Autowired
    private UserMoneyLogMapper userMoneyLogMapper;

    @Override
    public boolean addUserMoneyLog(UserMoneyLog userMoneyLog) {
        try {
//            long uuid = Math.abs(UUID.randomUUID().getMostSignificantBits());
//            userMoneyLog.setOrderId(uuid);
            userMoneyLog.setCreateTime(new Date(System.currentTimeMillis()));
            userMoneyLogMapper.insert(userMoneyLog);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserMoneyLog(UserMoneyLog userMoneyLog) {

        try {
            userMoneyLogMapper.updateByPrimaryKeySelective(userMoneyLog);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public UserMoneyLog getUserMoneyLogByUserId(long userId) {
        UserMoneyLogExample userMoneyLogExample = new UserMoneyLogExample();
        UserMoneyLogExample.Criteria criteria = userMoneyLogExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        List<UserMoneyLog> userMoneyLogs = userMoneyLogMapper.selectByExample(userMoneyLogExample);
        if (userMoneyLogs == null || userMoneyLogs.size() != 1) {
            return null;
        }

        return userMoneyLogs.get(0);
    }

    @Override
    public List<UserMoneyLog> getAllUserMoneyLog() {
        UserMoneyLogExample userMoneyLogExample = new UserMoneyLogExample();

        return userMoneyLogMapper.selectByExample(userMoneyLogExample);
    }
}
