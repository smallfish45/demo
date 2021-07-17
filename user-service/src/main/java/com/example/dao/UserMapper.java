package com.example.dao;

import com.example.pojo.User;
import com.example.pojo.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    List<User> selectByExample(UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);
}
