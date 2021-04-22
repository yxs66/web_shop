package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.mapper.ProductBrandMapper;
import com.yyy.springboot.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return mapper.selectProductBrandByProductTypeId(productTypeId);
    }

    @Override
    @Transactional
    public void insertProductBrand(ProductBrand productBrand) {
        ProductBrand p = mapper.selectOne(new QueryWrapper<ProductBrand>().eq("name", productBrand.getName()));
        if (p == null) {
            mapper.insert(productBrand);
        }else {
            productBrand.setId(p.getId());
        }
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
