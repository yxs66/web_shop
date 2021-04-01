package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductRepertory;
import com.yyy.springboot.mapper.ProductRepertoryMapper;
import com.yyy.springboot.service.ProductRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepertoryServiceImpl implements ProductRepertoryService {

    @Autowired
    private ProductRepertoryMapper mapper;

    @Override
    public List<ProductRepertory> selectProductRepertory() {
        return mapper.selectList(null);
    }

    @Override
    public ProductRepertory selectProductRepertoryById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public void insertProductRepertory(ProductRepertory productRepertory) {
        mapper.insert(productRepertory);
    }

    @Override
    public void deleteProductRepertoryById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductRepertoryById(ProductRepertory productRepertory) {
        mapper.updateById(productRepertory);
    }
}
