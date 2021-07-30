package com.example.service;

import com.example.pojo.Order;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 20805693
 */
public interface OrderService {

    void addOrder(String username, String address, int goodsNumber, double goodsPrice) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException;

    List<Order> getAllOrder();

}
