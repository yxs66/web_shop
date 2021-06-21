package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyy.springboot.entitys.ProductRepertory;
import com.yyy.springboot.mapper.ProductRepertoryMapper;
import com.yyy.springboot.service.ProductRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ProductRepertory selectProductRepertoryByProductId(Long productId) {
        return mapper.selectOne(new QueryWrapper<ProductRepertory>().eq("product_id", productId));
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

    @Override
    public ProductRepertory selectProductRepertoryByPsdIds(List<Long> psdIds) {
        return mapper.selectProductRepertoryByPsdIds(psdIds);
    }

    @Override
    public void subtractProductRepertoryNumById(Long id, Integer num) {
        mapper.subtractProductRepertoryNumById(id, num);
    }

    @Override
    public void addProductRepertoryNumById(Long id, Integer num) {
        mapper.addProductRepertoryNumById(id, num);
    }
}
