package com.yyy.springboot.controller;

import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderMasterService orderMasterService;


}
