package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.dto.ProductDetailNumDTO;
import com.yyy.springboot.entitys.*;
import com.yyy.springboot.exception.SQLInsertException;
import com.yyy.springboot.mapper.ProductMapper;
import com.yyy.springboot.service.*;
import com.yyy.springboot.util.ResultUtil;
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
    private ProductSpecificationDetailService productSpecificationDetailService;
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
        log.info("selectProductRepertoryMidPsdIdsByProductId:" + list);
        return list;
    }

    @Override
    public ProductAmountDTO selectProductAmountDTOByProductId(Long productId, Long[] psdId) {
        List<Long> longs = Arrays.asList(psdId);
        List<ProductAmountDTO> productAmountDTOS = getProductRepertoryMidIds(productId);
        log.info("selectProductAmountDTOByProductId:" + productAmountDTOS.toString());
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
        if (productAmountDTOS == null || productAmountDTOS.size() == 0)
            return null;
        else
            return productAmountDTOS.get(0);
    }

    @Override
    public void insertProduct(Product product) {
   /*     Product p = mapper.selectOne(new QueryWrapper<Product>().eq("name", product.getName()));
        if (p == null) {
            mapper.insert(product);
        } else {
            product.setId(p.getId());
        }*/
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("user_id", product.getUserId());
        Product p = mapper.selectOne(new QueryWrapper<Product>().allEq(map));
        if (p == null) {
            mapper.insert(product);
        } else {
            throw new SQLInsertException(ResultUtil.repeatProductFail());
        }
    }

    @Transactional
    @Override
    public void insertProductDetailDTO(ProductDetailDTO productDetailDTO) {
        /*
        ProductType productType = new ProductType(null, productDetailDTO.getTypeName());
        productTypeService.insertProductType(productType);
        ProductBrand productBrand = new ProductBrand(null, productType.getId(), productDetailDTO.getBrandName(), productDetailDTO.getBrandImage());
        productBrandService.insertProductBrand(productBrand);
        Product product = new Product(null, productDetailDTO.getProductName(), productBrand.getId(), productType.getId(), productDetailDTO.getProductImage(),productDetailDTO.getUserId());

        Map<String, String> map = productDetailDTO.getProductSpecificationDetail();
        if(map==null||map.size()==0)
            mapper.insert(product);
        else
            this.insertProduct(product);
        ProductRepertory productRepertory = new ProductRepertory(null, product.getId(), productDetailDTO.getAmount(), productDetailDTO.getNum());
        productRepertoryService.insertProductRepertory(productRepertory);

        if(map==null||map.size()==0)
            return;
        List<Long> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>(map.values());
        for (String key : map.keySet()) {
            ProductSpecification productSpecification = new ProductSpecification(null, product.getId(), key);
            productSpecificationService.insertProductSpecification(productSpecification);
            keys.add(productSpecification.getId());
        }
        productSpecificationDetailService.isExistProductSpecificationDetail(keys, values);

        for (int i = 0; i < values.size(); i++) {
            ProductSpecificationDetail productSpecificationDetail = new ProductSpecificationDetail(null, keys.get(i), values.get(i));
            productSpecificationDetailService.insertProductSpecificationDetail(productSpecificationDetail);
            ProductRepertoryMid productRepertoryMid = new ProductRepertoryMid(null, productRepertory.getId(), productSpecificationDetail.getId());
            productRepertoryMidService.insertProductRepertoryDetail(productRepertoryMid);
        }*/
        ProductType productType = new ProductType(null, productDetailDTO.getTypeName());
        productTypeService.insertProductType(productType);
        ProductBrand productBrand = new ProductBrand(null, productType.getId(), productDetailDTO.getBrandName(), productDetailDTO.getBrandImage());
        productBrandService.insertProductBrand(productBrand);
        Product product = new Product(null, productDetailDTO.getProductName(), productBrand.getId(), productType.getId(), productDetailDTO.getProductImage(), productDetailDTO.getUserId());
        this.insertProduct(product);
        List<ProductDetailNumDTO> productDetailNumDTOS = productDetailDTO.getProductDetailNumDTOS();
        if (productDetailNumDTOS == null || productDetailNumDTOS.size() == 0)
            throw new SQLInsertException(ResultUtil.productParamIllegal());

        for (ProductDetailNumDTO productDetailNumDTO : productDetailNumDTOS) {
            Map<String, String> map = productDetailNumDTO.getProductSpecificationDetail();
            if (map == null || map.size() == 0) {
                if (productRepertoryMidService.selectProductRepertoryMidNullCountByProductId(product.getId()) > 0)
                    throw new SQLInsertException(ResultUtil.repeatProductFail());
            }
            ProductRepertory productRepertory = new ProductRepertory(null, product.getId(), productDetailNumDTO.getAmount(), productDetailNumDTO.getNum());
            productRepertoryService.insertProductRepertory(productRepertory);

            if (map == null || map.size() == 0)
                continue;

            function(map, product.getId(), productRepertory.getId());

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

    @Override
    @Transactional
    public void insertProductSpecificationDetailByProductId(Long productId, ProductDetailDTO productDetailDTO) {
        List<ProductDetailNumDTO> productDetailNumDTOS = productDetailDTO.getProductDetailNumDTOS();
        if (productDetailNumDTOS == null || productDetailNumDTOS.size() == 0)
            throw new SQLInsertException(ResultUtil.productParamIllegal());
        ProductDetailNumDTO productDetailNumDTO = productDetailNumDTOS.get(0);//只插入第一行

        Map<String, String> map = productDetailNumDTO.getProductSpecificationDetail();
        if (map == null || map.size() == 0) {
            if (productRepertoryMidService.selectProductRepertoryMidNullCountByProductId(productId) > 0)
                throw new SQLInsertException(ResultUtil.repeatProductFail());
        }
        ProductRepertory productRepertory = new ProductRepertory(null, productId, productDetailNumDTO.getAmount(), productDetailNumDTO.getNum());
        productRepertoryService.insertProductRepertory(productRepertory);

        if (map == null || map.size() == 0)
            return;

        function(map, productId, productRepertory.getId());
    }

    public void function(Map<String, String> map, Long productId, Long productRepertoryId) {
        List<Long> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>(map.values());
        for (String key : map.keySet()) {
            ProductSpecification productSpecification = new ProductSpecification(null, productId, key);
            productSpecificationService.insertProductSpecification(productSpecification);
            keys.add(productSpecification.getId());
        }

        productSpecificationDetailService.isExistProductSpecificationDetail(productId, keys, values);

        for (int i = 0; i < values.size(); i++) {
            ProductSpecificationDetail productSpecificationDetail = new ProductSpecificationDetail(null, keys.get(i), values.get(i));
            productSpecificationDetailService.insertProductSpecificationDetail(productSpecificationDetail);
            ProductRepertoryMid productRepertoryMid = new ProductRepertoryMid(null, productRepertoryId, productSpecificationDetail.getId());
            productRepertoryMidService.insertProductRepertoryDetail(productRepertoryMid);
        }
    }
}
