package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductSpecification;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductSpecificationMapper extends BaseMapper<ProductSpecification> {

    @Select("select * from product_specification where product_id=#{id}")
    List<ProductSpecification> selectA(Long id);
}
