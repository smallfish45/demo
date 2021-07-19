package com.example.service;

import com.example.pojo.User;
import com.example.pojo.UserMoneyLog;

import java.util.List;

public interface UserMoneyLogService {

    boolean addUserMoneyLog(UserMoneyLog userMoneyLog);

    boolean updateUserMoneyLog(UserMoneyLog userMoneyLog);

    UserMoneyLog getUserMoneyLogByUserId(long userId);

    List<UserMoneyLog> getAllUserMoneyLog();

}
