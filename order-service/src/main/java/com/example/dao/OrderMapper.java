package com.example.dao;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 20805693
 */
@Mapper
public interface OrderMapper {

    /**
     * add order
     * @param order
     * @return
     */
    int addOrder(@Param("order") Order order);

    /**
     * get all order
     * @return
     */
    List<Order> getAllOrder();

}
