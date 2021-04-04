package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductSpecification;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductSpecificationMapper extends BaseMapper<ProductSpecification> {

    @Select("select * from product_specification")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "productSpecificationDetails", many = @Many(select = "com.yyy.springboot.mapper.ProductSpecificationDetailMapper.selectProductSpecificationDetailByPs_id"))
    })
    List<ProductSpecification> selectProductSpecifications();

    @Insert("insert into product_specification(product_id,name) select #{productSpecification.productId},#{productSpecification.name} where not exists (select id from product_specification where name=#{productSpecification.name} and product_id=#{productSpecification.productId})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    int insertProductSpecification(@Param("productSpecification") ProductSpecification productSpecification);
}
