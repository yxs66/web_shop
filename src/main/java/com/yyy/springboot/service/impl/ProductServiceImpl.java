package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.*;
import com.yyy.springboot.mapper.ProductMapper;
import com.yyy.springboot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductSpecificationService productSpecificationService;
    @Autowired
    private ProductSpecificationDetailServiceImpl productSpecificationDetailService;
    @Autowired
    private ProductRepertoryService productRepertoryService;
    @Autowired
    private ProductRepertoryMidService productRepertoryMidService;

    @Override
    public List<Product> selectProduct() {
        return mapper.selectList(null);
    }

    @Override
    public Product selectProductById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Product> selectProductByProductTypeIdAndProductBranId(Integer productTypeId, Integer productBrandId) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("type_id", productTypeId);
        hashMap.put("brand_id", productBrandId);
        return mapper.selectList(new QueryWrapper<Product>().allEq(hashMap, false)); //null2IsNull:为true则在map的value为null时调用sql的 is null ,为false时则忽略

    }

    @Override
    public ProductDTO selectProductDetailDTOByProductId(Long id) {
        return mapper.selectProductDetailDTOByProductId(id);
    }

    public List<ProductAmountDTO> getProductRepertoryMidIds(Long productId) {
        List<ProductAmountDTO> productAmountDTOS = mapper.selectProductAmountDTOByProductId(productId);
        return productAmountDTOS;
    }

    @Override
    public List<List<Long>> selectProductRepertoryMidPsdIdsByProductId(Long productId) {
        List<List<Long>> list = new ArrayList<>();
        List<ProductAmountDTO> productAmountDTOS = getProductRepertoryMidIds(productId);
        for (ProductAmountDTO paDTO : productAmountDTOS) {
            List<ProductRepertoryMid> productRepertoryMids = paDTO.getProductRepertoryMids();
            List<Long> psdIds = productRepertoryMids.stream()
                    .map(x -> {
                        return x.getPsdId();
                    }).collect(Collectors.toList());
            list.add(psdIds);
        }
        log.info("selectProductRepertoryMidPsdIdsByProductId:"+list);
        return list;
    }

    @Override
    public ProductAmountDTO selectProductAmountDTOByProductId(Long productId, Long[] psdId) {
        List<Long> longs = Arrays.asList(psdId);
        List<ProductAmountDTO> productAmountDTOS = getProductRepertoryMidIds(productId);
        log.info("selectProductAmountDTOByProductId:"+productAmountDTOS.toString());
        for (ProductAmountDTO paDTO : productAmountDTOS) {
            List<ProductRepertoryMid> productRepertoryMids = paDTO.getProductRepertoryMids();
            if (productRepertoryMids.size() >= longs.size()) {
                List<Long> psdIds = productRepertoryMids.stream()
                        .map(x -> {
                            return x.getPsdId();
                        }).collect(Collectors.toList());
                if (psdIds.containsAll(longs)) {
                    return paDTO;
                }
            }
        }
        return productAmountDTOS.get(0);
    }

    @Override
    public void insertProduct(Product product) {
        mapper.insert(product);
    }

    @Transactional
    @Override
    public void insertProductDetailDTO(ProductDetailDTO productDetailDTO) {
        ProductType productType = new ProductType(null, productDetailDTO.getTypeName());
        productTypeService.insertProductType(productType);
        ProductBrand productBrand = new ProductBrand(null, productType.getId(), productDetailDTO.getBrandName(), productDetailDTO.getBrandImage());
        productBrandService.insertProductBrand(productBrand);
        Product product = new Product(productDetailDTO.getId(), productDetailDTO.getProductName(), productBrand.getId(), productType.getId(), productDetailDTO.getProductImage());
        insertProduct(product);

        ProductRepertory productRepertory = new ProductRepertory(null, product.getId(), productDetailDTO.getAmount(), productDetailDTO.getNum());
        productRepertoryService.insertProductRepertory(productRepertory);


        Map<String, String> map = productDetailDTO.getProductSpecificationDetail();
        for(Map.Entry<String,String> entry:map.entrySet()){
            ProductSpecification productSpecification = new ProductSpecification(null, product.getId(), entry.getKey());
            productSpecificationService.insertProductSpecification(productSpecification);
            ProductSpecificationDetail productSpecificationDetail = new ProductSpecificationDetail(null, productSpecification.getId(), entry.getValue());
            productSpecificationDetailService.insertProductSpecificationDetail(productSpecificationDetail);
            ProductRepertoryMid productRepertoryMid = new ProductRepertoryMid(null, productRepertory.getId(), productSpecificationDetail.getId());
            productRepertoryMidService.insertProductRepertoryDetail(productRepertoryMid);
        }
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
