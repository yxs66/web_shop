package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.Payment;
import com.yyy.springboot.mapper.PaymentMapper;
import com.yyy.springboot.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> selectPayments() {
        return paymentMapper.selectList( null);
    }

    @Override
    public Payment selectPaymentById(Long id) {
        return paymentMapper.selectById(id);
    }

    @Override
    public void insertPayment(Payment payment) {
        paymentMapper.insert(payment);
    }
}
