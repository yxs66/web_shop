package com.yyy.springboot.service;

import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface OrderMasterService {
    List<OrderMaster> selectOrderMasters();

    OrderMaster selectOrderMasterById(Long id);

    void insertOrderMaster(List<Integer> psdIds);

}
