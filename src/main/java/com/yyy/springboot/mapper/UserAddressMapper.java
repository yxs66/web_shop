package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAddressMapper extends BaseMapper<UserAddress> {
    @Update("update user_address set def=#{def} where user_address.user_openid=#{userOpenId}")
    int updateUserAddressDef(@Param("def") Byte def, @Param("userOpenId") Long userOpenId);
}
