package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.mapper.ProductTypeMapper;
import com.yyy.springboot.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper mapper;

    @Override
    public List<ProductType> selectProductType() {
        return mapper.selectList(null);
    }

    @Override
    public ProductType selectProductTypeById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public void insertProductType(ProductType productType) {
        mapper.insert(productType);
    }

    @Override
    public void deleteProductTypeById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductTypeById(ProductType productType) {
        mapper.updateById(productType);
    }
}
