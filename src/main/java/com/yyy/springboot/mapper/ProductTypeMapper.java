package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ProductTypeMapper extends BaseMapper<ProductType> {

    //不存在才插入
//    @Insert("insert into product_type(name) select #{productType.name} where not exists (select id from product_type where name=#{productType.name})")
//    @Insert("insert into product_type(name) select #{productType.name} on duplicate key update id=last_insert_id(id)") //前提时name字段有唯一索引或是主键索引
//    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
//    int insertProductType(@Param("productType") ProductType productType);
}
