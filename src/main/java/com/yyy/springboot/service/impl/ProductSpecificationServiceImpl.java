package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.mapper.ProductSpecificationMapper;
import com.yyy.springboot.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductSpecificationServiceImpl  extends ServiceImpl<ProductSpecificationMapper,ProductSpecification> implements ProductSpecificationService {

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
    @Transactional
    public void insertProductSpecification(ProductSpecification productSpecification) {
        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productSpecification.getProductId());
        map.put("name", productSpecification.getName());
        ProductSpecification p = mapper.selectOne(new QueryWrapper<ProductSpecification>().allEq(map));
        if (p == null) {
            mapper.insert(productSpecification);
        }else{
            productSpecification.setId(p.getId());
        }
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
