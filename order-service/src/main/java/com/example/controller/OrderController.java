package com.example.controller;

import com.example.pojo.Order;
import com.example.service.OrderService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 20805693
 * @Description: order
 * @date 2021/7/3014:54
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    public void addOrder(String username, String address, int goodsNumber, double goodsPrice) throws Exception {
        try {
            orderService.addOrder(username, address, goodsNumber, goodsPrice);
        } catch (UnsupportedEncodingException | MQBrokerException | RemotingException | InterruptedException | MQClientException e) {
            e.printStackTrace();
            throw new Exception("订单添加失败");
        }
    }

    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

}
