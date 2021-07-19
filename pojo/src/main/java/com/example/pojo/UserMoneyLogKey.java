package com.example.pojo;

import lombok.Data;

@Data
public class UserMoneyLogKey {
    private Long uuid;

    private Long userId;

    private Long orderId;

    private Integer moneyLogType;

}
