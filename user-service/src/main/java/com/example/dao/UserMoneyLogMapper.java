package com.example.dao;

import com.example.pojo.UserMoneyLog;
import com.example.pojo.UserMoneyLogExample;
import com.example.pojo.UserMoneyLogKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMoneyLogMapper {

    int deleteByExample(UserMoneyLogExample example);

    int insert(UserMoneyLog record);

    List<UserMoneyLog> selectByExample(UserMoneyLogExample example);

    UserMoneyLog selectByPrimaryKey(UserMoneyLogKey key);

    int updateByPrimaryKeySelective(UserMoneyLog record);

    int updateByPrimaryKey(UserMoneyLog record);

}
