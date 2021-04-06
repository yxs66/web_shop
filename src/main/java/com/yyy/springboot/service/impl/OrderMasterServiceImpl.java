package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.mapper.OrderMasterMapper;
import com.yyy.springboot.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public List<OrderMaster> selectOrderMasters() {
        return orderMasterMapper.selectList(null);
    }

    @Override
    public OrderMaster selectOrderMasterById(Long id) {
        return orderMasterMapper.selectById(id);
    }

    @Override
    public void insertOrderMaster(OrderMaster orderMaster) {

        orderMasterMapper.insert(orderMaster);
    }
}
