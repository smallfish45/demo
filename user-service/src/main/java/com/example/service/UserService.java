package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean updateUser(User user);

    User getUserByName(String name);

    List<User> getAllUser();

}
