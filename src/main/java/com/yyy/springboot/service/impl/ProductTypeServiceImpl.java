package com.yyy.springboot.service.impl;

import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.mapper.ProductTypeMapper;
import com.yyy.springboot.service.ProductBrandService;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper typeMapper;
    @Autowired
    private ProductBrandService brandService;
    @Autowired
    private ProductService productService;

    @Override
    public List<ProductType> selectProductType() {
        return typeMapper.selectList(null);
    }

    @Override
    public ProductType selectProductTypeById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void insertProductType(ProductType productType) {
        typeMapper.insert(productType);
    }

    @Override
    public void deleteProductTypeById(Integer id) {
        typeMapper.deleteById(id);
    }

    @Override
    public void updateProductTypeById(ProductType productType) {
        typeMapper.updateById(productType);
    }

    @Override
    public ProductAndBrandDTO selectProductAndBrandDtoByProductTypeId(Integer id) {
        ProductAndBrandDTO productAndBrandDto = new ProductAndBrandDTO();

        productAndBrandDto.setProductBrands(brandService.selectProductBrandByProductTypeId(id));
        productAndBrandDto.setProducts(productService.selectProductByProductTypeIdAndProductBranId(id, null));

        return productAndBrandDto;
    }
}
