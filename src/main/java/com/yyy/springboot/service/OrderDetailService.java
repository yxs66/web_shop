package com.yyy.springboot.service;

import com.yyy.springboot.entitys.OrderDetail;
import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> selectOrderDetails();

    OrderDetail selectOrderDetailById(Long id);

    void insertOrderDetail(OrderDetail orderDetail);
}
