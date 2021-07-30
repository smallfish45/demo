package com.example.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 20805693
 */
@Data
public class Order implements Serializable {

    private long orderId;

    private long userId;

    /**
     * 订单状态 0未确认 1已确认 2已取消 3无效 4退款
     */
    private int orderStatus;

    /**
     * 付状态 0未支付 1支付中 2已支付
     */
    private int payStatus;

    private String address;

    private long goodsId;

    private int goodsNumber;

    private double goodsPrice;

    private double orderAmount;

    private double payAmount;

    private Timestamp payTime;
}
