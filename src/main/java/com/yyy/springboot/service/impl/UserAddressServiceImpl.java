package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyy.springboot.entitys.UserAddress;
import com.yyy.springboot.exception.MySQLException;
import com.yyy.springboot.mapper.UserAddressMapper;
import com.yyy.springboot.service.UserAddressService;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.util.ShareThreadLocal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper mapper;

    @Autowired
    private ShareThreadLocal<Long> shareThreadLocal;

    @Override
    public List<UserAddress> selectUserAddress() {
        return mapper.selectList(null);
    }

    @Override
    public UserAddress selectUserAddressById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<UserAddress> selectUserAddressByUserId() {
        return mapper.selectList(new QueryWrapper<UserAddress>().eq("user_openid",shareThreadLocal.get()));
    }

    @Override
    public UserAddress selectUserAddressByDef() {
        return mapper.selectOne(new QueryWrapper<UserAddress>().eq("def", 1).eq("user_openid",shareThreadLocal.get()));
    }

    @Override
    @Transactional
    public void insertUserAddress(UserAddress userAddress) {
        if(userAddress.getDef()!=null&&userAddress.getDef()==1){
            updateUserAddressDefById(new UserAddress().setDef(Byte.valueOf("0")).setUserOpenId(shareThreadLocal.get()));
        }else{
            Integer count = mapper.selectCount(new QueryWrapper<UserAddress>().eq("user_openid", shareThreadLocal.get()));
            if(count==0)
                userAddress.setDef(Byte.valueOf("1"));
        }
        userAddress.setUserOpenId(shareThreadLocal.get());
        mapper.insert(userAddress);
    }

    @Override
    public void deleteUserAddressById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateUserAddressById(UserAddress userAddress) {
        UpdateWrapper<UserAddress> updateWrapper = new UpdateWrapper<UserAddress>().eq("id", userAddress.getId())
                .set("name", userAddress.getName())
                .set("phone", userAddress.getPhone())
                .set("address", userAddress.getAddress())
                .set("def", userAddress.getDef());
        mapper.update(null, updateWrapper);
    }

    @Override
    public void updateUserAddressDefById(UserAddress userAddress) {
        mapper.update(userAddress, new UpdateWrapper<UserAddress>().eq("user_openid", shareThreadLocal.get()));
    }

    @Override
    @Transactional
    public void updateUserAddressDefById(Long id) {
        updateUserAddressDefById(new UserAddress().setDef(Byte.valueOf("0")).setUserOpenId(shareThreadLocal.get()));

        if(mapper.update(new UserAddress().setDef(Byte.valueOf("1")), new UpdateWrapper<UserAddress>().eq("user_openid", shareThreadLocal.get()).eq("id", id))==0)
            throw new MySQLException(ResultUtil.illegalOperationInParam());

    }
}
