package com.yyy.springboot.service;

import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.entitys.Payment;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface PaymentService {
    List<Payment> selectPayments();

    Payment selectPaymentById(Long id);

    void insertPayment(Payment payment);
}
