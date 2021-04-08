package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyy.springboot.entitys.UserAddress;
import com.yyy.springboot.mapper.UserAddressMapper;
import com.yyy.springboot.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper mapper;

    @Override
    public List<UserAddress> selectUserAddress() {
        return mapper.selectList(null);
    }

    @Override
    public UserAddress selectUserAddressById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<UserAddress> selectUserAddressByUserId(Long userId) {
        return mapper.selectList(new QueryWrapper<UserAddress>().eq("user_openid",userId));
    }

    @Override
    public UserAddress selectUserAddressByDef(Long userId) {
        return mapper.selectOne(new QueryWrapper<UserAddress>().eq("def", 1).eq("user_openid",userId));
    }

    @Override
    @Transactional
    public void insertUserAddress(UserAddress userAddress) {
        if(userAddress.getDef()!=null&&userAddress.getDef()==1){
            updateUserAddressDef(Byte.valueOf("0"), userAddress.getUserOpenId());
        }else{
            Integer count = mapper.selectCount(new QueryWrapper<UserAddress>().eq("user_openid", userAddress.getUserOpenId()));
            if(count==0)
                userAddress.setDef(Byte.valueOf("1"));
        }
        mapper.insert(userAddress);
    }

    @Override
    public void deleteUserAddressById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateUserAddressById(UserAddress userAddress) {
        mapper.updateById(userAddress);
    }

    @Override
    public void updateUserAddressDef(Byte def,Long userOpenId) {
        mapper.updateUserAddressDef(def, userOpenId);
    }
}
