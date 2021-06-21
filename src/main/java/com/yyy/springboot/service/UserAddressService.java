package com.yyy.springboot.service;

import com.yyy.springboot.entitys.User;
import com.yyy.springboot.entitys.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> selectUserAddress();

    UserAddress selectUserAddressById(Long id);

    List<UserAddress> selectUserAddressByUserId();

    UserAddress selectUserAddressByDef();

    void insertUserAddress(UserAddress userAddress);

    void deleteUserAddressById(Long id);

    void updateUserAddressById(UserAddress userAddress);

    void updateUserAddressDefById(UserAddress userAddress);

    void updateUserAddressDefById(Long id);
}
