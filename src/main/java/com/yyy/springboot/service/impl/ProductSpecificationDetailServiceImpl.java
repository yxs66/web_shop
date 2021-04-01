package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import com.yyy.springboot.mapper.ProductSpecificationDetailMapper;
import com.yyy.springboot.service.ProductSpecificationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecificationDetailServiceImpl implements ProductSpecificationDetailService {
    @Autowired
    private ProductSpecificationDetailMapper mapper;

    @Override
    public List<ProductSpecificationDetail> selectProductSpecificationDetails() {
        return mapper.selectList(null);
    }

    @Override
    public ProductSpecificationDetail selectProductSpecificationDetailById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<ProductSpecificationDetail> selectProductSpecificationDetailBySpecificationId(Long specificationId) {
        return mapper.selectList(new QueryWrapper<ProductSpecificationDetail>().eq("ps_id", specificationId));
    }

    @Override
    public void insertProductSpecificationDetail(ProductSpecificationDetail productSpecificationDetail) {
        mapper.insert(productSpecificationDetail);
    }

    @Override
    public void deleteProductSpecificationDetailById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateProductSpecificationDetailById(ProductSpecificationDetail productSpecificationDetail) {
        mapper.updateById(productSpecificationDetail);
    }
}
