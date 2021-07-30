package com.example.dao;

import com.example.pojo.User;
import com.example.pojo.UserSqlCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    List<User> selectByExample(UserSqlCondition example);

    int updateByExample(@Param("record") User record, @Param("example") UserSqlCondition example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserSqlCondition example);
}
