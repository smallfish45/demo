package com.example.service.impl;

import com.example.dao.OrderMapper;
import com.example.pojo.Order;
import com.example.pojo.User;
import com.example.service.IdService;
import com.example.service.OrderService;
import com.example.service.UserService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 20805693
 * @Description: 订单Service
 * @date 2021/7/3014:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private DefaultMQProducer producer;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IdService idService;

    @Resource
    private UserService userService;

    @Override
    public void addOrder(String username, String address, int goodsNumber, double goodsPrice) throws MQBrokerException, RemotingException, InterruptedException, MQClientException, UnsupportedEncodingException {

        Order order = new Order();

        long orderId = Long.parseLong(idService.getSnowflakeId());
        long goodsId = Long.parseLong(idService.getSnowflakeId());
        User user = userService.getUserByName("postman01");

        order.setOrderId(orderId);
        order.setUserId(user.getUserId());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setAddress(address);
        order.setGoodsId(goodsId);
        order.setGoodsNumber(goodsNumber);
        order.setGoodsPrice(goodsPrice);
        order.setOrderAmount(goodsPrice * goodsNumber);
        order.setPayAmount(0.0);
        order.setPayTime(Timestamp.valueOf(LocalDateTime.now()));

        orderMapper.addOrder(order);

        Message message = new Message("TopicTest", String.valueOf(orderId).getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = producer.send(message);

        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", result);

    }

    @Override
    public List<Order> getAllOrder() {
        return orderMapper.getAllOrder();
    }

}
