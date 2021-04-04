package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.dto.TypeBrandSpecificationDTO;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.mapper.ProductBrandMapper;
import com.yyy.springboot.mapper.ProductSpecificationMapper;
import com.yyy.springboot.mapper.ProductTypeMapper;
import com.yyy.springboot.service.ProductBrandService;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper,ProductType> implements ProductTypeService {
    @Autowired
    private ProductTypeMapper typeMapper;
    @Autowired
    private ProductBrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSpecificationMapper ProductSpecificationMapper;

    @Override
    public List<ProductType> selectProductType() {
        return typeMapper.selectList(null);
    }

    @Override
    public ProductType selectProductTypeById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Transactional
    @Override
    public void insertProductType(ProductType productType) {
        ProductType name = typeMapper.selectOne(new QueryWrapper<ProductType>().eq("name", productType.getName()));
        if (name == null) {
            int insert = typeMapper.insert(productType);
        }
        System.out.println(name);

//        typeMapper.insertProductType(productType);
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

    @Override
    public TypeBrandSpecificationDTO selectTypeBrandSpecificationDTO() {
        List<ProductType> productTypes = this.selectProductType();
        List<ProductBrand> productBrands = brandService.selectProductBrand();
        List<ProductSpecification> productSpecifications = ProductSpecificationMapper.selectProductSpecifications();
        return new TypeBrandSpecificationDTO().setProductTypes(productTypes).setProductBrands(productBrands).setProductSpecifications(productSpecifications);
    }

}
