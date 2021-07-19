package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public User getUserByName(@RequestParam(value = "name", required = true) String name) {
        return userService.getUserByName(name);
    }

    @PostMapping()
    public void addUser(User user) {
        userService.addUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

}
