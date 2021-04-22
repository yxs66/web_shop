package com.yyy.springboot.service.impl;

import com.sun.xml.internal.bind.v2.TODO;
import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.entitys.ProductRepertory;
import com.yyy.springboot.exception.MySQLException;
import com.yyy.springboot.mapper.OrderMasterMapper;
import com.yyy.springboot.service.OrderMasterService;
import com.yyy.springboot.service.ProductRepertoryService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private ProductRepertoryService repertoryService;

    @Override
    public List<OrderMaster> selectOrderMasters() {
        return orderMasterMapper.selectList(null);
    }

    @Override
    public OrderMaster selectOrderMasterById(Long id) {
        return orderMasterMapper.selectById(id);
    }

    @Override
    @Transactional
    // TODO 暂时加synchronized ，后面改redis分布式锁
    public synchronized void insertOrderMaster(List<Integer> psdIds) {
        ProductRepertory productRepertory = repertoryService.selectProductRepertoryByPsdIds(psdIds);
        if (ObjectUtils.isEmpty(productRepertory))
            throw new MySQLException(ResultUtil.productNonexistent());
        if (productRepertory.getNum()<=0)
            throw new MySQLException(ResultUtil.productUnderStock());


//        orderMasterMapper.insert(orderMaster);
    }
}
