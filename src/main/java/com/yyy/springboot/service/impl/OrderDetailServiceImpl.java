package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.OrderDetail;
import com.yyy.springboot.mapper.OrderDetailMapper;
import com.yyy.springboot.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> selectOrderDetails() {
        return orderDetailMapper.selectList(null);
    }

    @Override
    public OrderDetail selectOrderDetailById(Long id) {
        return orderDetailMapper.selectById(id);
    }

    @Override
    public void insertOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insert(orderDetail);
    }
}
