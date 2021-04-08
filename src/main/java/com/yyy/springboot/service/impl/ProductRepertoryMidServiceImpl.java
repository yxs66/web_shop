package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductRepertoryMid;
import com.yyy.springboot.mapper.ProductRepertoryMidMapper;
import com.yyy.springboot.service.ProductRepertoryMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepertoryMidServiceImpl implements ProductRepertoryMidService {

    @Autowired
    private ProductRepertoryMidMapper mapper;

    @Override
    public List<ProductRepertoryMid> selectProductRepertoryDetail() {
        return mapper.selectList(null);
    }

    @Override
    public ProductRepertoryMid selectProductRepertoryDetailById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public void insertProductRepertoryDetail(ProductRepertoryMid productRepertoryDetail) {
        mapper.insert(productRepertoryDetail);
    }

    @Override
    public void deleteProductRepertoryDetailById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductRepertoryDetailById(ProductRepertoryMid productRepertoryDetail) {
        mapper.updateById(productRepertoryDetail);
    }

    @Override
    public Integer selectProductRepertoryMidNullCountByProductId(Long productId) {
        return mapper.selectProductRepertoryMidNullCountByProductId(productId);
    }

}
