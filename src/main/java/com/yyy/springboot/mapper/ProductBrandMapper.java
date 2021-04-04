package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductBrand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ProductBrandMapper extends BaseMapper<ProductBrand> {

//    @Insert("insert into product_brand(product_type_id,name,image) select #{productBrand.productTypeId},#{productBrand.name},#{productBrand.image}" +
//            " where not exists (select id from product_brand where name=#{productBrand.name})")
//    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
//    int insertProductBrand(@Param("productBrand") ProductBrand productBrand);

}
