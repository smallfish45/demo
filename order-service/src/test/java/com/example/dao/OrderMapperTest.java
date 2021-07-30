package com.example.dao;

import com.example.pojo.Order;
import com.example.pojo.User;
import com.example.service.IdService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class OrderMapperTest {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IdService idService;

    @Resource
    private UserService userService;


    @Test
    void addOrder() {

        Order order = new Order();

        long orderId = Long.parseLong( idService.getSnowflakeId() );
        long goodsId = Long.parseLong( idService.getSnowflakeId() );
        User user = userService.getUserByName("postman01");

        order.setOrderId(orderId);
        order.setUserId(user.getUserId());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setAddress("xtc");
        order.setGoodsId(goodsId);
        order.setGoodsNumber(1);
        order.setGoodsPrice(124.44);
        order.setOrderAmount(124.44);
        order.setPayAmount(0.0);
        order.setPayTime(Timestamp.valueOf(LocalDateTime.now()));

        orderMapper.addOrder(order);
    }

    @Test
        void getAllOrder() {
        System.out.println("-------------------------------------------------------------");
        orderMapper.getAllOrder().forEach(System.out::println);
        System.out.println("-------------------------------------------------------------");
    }
}