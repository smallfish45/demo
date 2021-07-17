package com.example.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long uuid;

    private Long userId;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Integer userScore;

    private Date userRegTime;

    private Long userMoney;

}