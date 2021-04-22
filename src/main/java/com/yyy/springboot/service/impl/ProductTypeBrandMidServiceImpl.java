package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductTypeBrandMid;
import com.yyy.springboot.mapper.ProductTypeBrandMidMapper;
import com.yyy.springboot.service.ProductTypeBrandMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/22 16:14
 * @Version 1.0
 **/
@Service
public class ProductTypeBrandMidServiceImpl implements ProductTypeBrandMidService {
    @Autowired
    ProductTypeBrandMidMapper mapper;

    @Override
    public void insertProductTypeBrandMid(@Valid ProductTypeBrandMid productTypeBrandMid) {
        ProductTypeBrandMid p = mapper.selectOne(new QueryWrapper<ProductTypeBrandMid>().eq("type_id", productTypeBrandMid.getTypeId()).eq("brand_id",productTypeBrandMid.getBrandId()));
        if (p == null) {
            mapper.insert(productTypeBrandMid);
        }else {
            productTypeBrandMid.setId(p.getId());
        }
    }
}
