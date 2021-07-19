package com.example.controller;

import com.example.pojo.UserMoneyLog;
import com.example.service.UserMoneyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user/moneylog")
public class UserMoneyLogController {

    @Autowired
    private UserMoneyLogService userMoneyLogService;

    @GetMapping("all")
    public List<UserMoneyLog> getAllUserMoneyLog() {
        return userMoneyLogService.getAllUserMoneyLog();
    }

}
