package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.mapper.ProductBrandMapper;
import com.yyy.springboot.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandMapper mapper;
    @Override
    public List<ProductBrand> selectProductBrand() {
        return mapper.selectList(null);
    }

    @Override
    public ProductBrand selectProductBrandById(Integer id) {
        return mapper.selectById(id);
    }

    public List<ProductBrand> selectProductBrandByProductTypeId(Integer productTypeId) {
        return mapper.selectList(new QueryWrapper<ProductBrand>().eq("product_type_id", productTypeId));
    }

    @Override
    public void insertProductBrand(ProductBrand productBrand) {
        productBrand.setImage("----");
        mapper.insertProductBrand(productBrand);
    }

    @Override
    public void deleteProductBrandById(Integer id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductBrandById(ProductBrand productBrand) {
        mapper.updateById(productBrand);
    }
}
