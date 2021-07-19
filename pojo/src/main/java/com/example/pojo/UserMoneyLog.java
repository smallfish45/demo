package com.example.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserMoneyLog extends UserMoneyLogKey implements Serializable {

    private BigDecimal useMoney;

    private Date createTime;

}
