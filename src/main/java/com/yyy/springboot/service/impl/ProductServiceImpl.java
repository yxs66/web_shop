package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.mapper.ProductMapper;
import com.yyy.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> selectProduct() {
        return mapper.selectList(null);
    }

    @Override
    public Product selectProductById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Product> selectProductByProductTypeIdAndProductBranId(Long productTypeId,Long productBrandId){
        HashMap<String, Long> hashMap = new HashMap<>();
        hashMap.put("type_id", productTypeId);
        hashMap.put("brand_id", productBrandId);
        return mapper.selectList(new QueryWrapper<Product>().allEq(hashMap, false)); //null2IsNull:为true则在map的value为null时调用sql的 is null ,为false时则忽略

    }


    @Override
    public void insertProduct(Product product) {
        product.setId(null);
        mapper.insert(product);
    }

    @Override
    public void deleteProductById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductById(Product product) {
        mapper.updateById(product);
    }
}
