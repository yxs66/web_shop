package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductRepertoryMid;
import com.yyy.springboot.mapper.ProductMapper;
import com.yyy.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public ProductAmountDTO selectProductAmountDTOByProductId(Long ProductId, Long[] psdId) {
        HashSet<Long> integers = new HashSet<>(Arrays.asList(psdId));
        System.out.println(integers);
        List<ProductAmountDTO> productAmountDTOS = mapper.selectProductAmountDTOByProductId(ProductId);
        for (ProductAmountDTO paDTO:productAmountDTOS) {
            List<ProductRepertoryMid> productRepertoryMids = paDTO.getProductRepertoryMids();
            Stream<Long> stream = Arrays.stream(psdId);
            Set<Long> collect = productRepertoryMids.stream()
                    .map(x -> {
                        return x.getPsdId();
                    }).collect(Collectors.toSet());
            System.out.println(collect);
            System.out.println(collect.containsAll(integers));
            if(collect.containsAll(integers)){
                return paDTO;
            }
//            if (productRepertoryMids.size() >= psdId.length) {
//                for (int i=0;i<productRepertoryMids.size();i++) {
//
//
//                }
//            }
        }
        return productAmountDTOS.get(0);
    }


    @Override
    public void insertProduct(Product product) {
        product.setImage("-----");
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
