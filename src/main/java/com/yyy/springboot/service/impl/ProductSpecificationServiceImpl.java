package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.mapper.ProductSpecificationMapper;
import com.yyy.springboot.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper mapper;

    @Override
    public List<ProductSpecification> selectProductSpecifications() {
        return mapper.selectList(null);
    }

    @Override
    public ProductSpecification selectProductSpecificationById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ProductSpecification> selectProductSpecificationByProductId(Long productId) {
        return mapper.selectList(new QueryWrapper<ProductSpecification>().eq("product_id", productId));
    }

    @Override
    public void insertProductSpecification(ProductSpecification productSpecification) {
        mapper.insertProductSpecification(productSpecification);
    }

    @Override
    public void deleteProductSpecificationById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductSpecificationById(ProductSpecification productSpecification) {
        mapper.updateById(productSpecification);
    }
}
