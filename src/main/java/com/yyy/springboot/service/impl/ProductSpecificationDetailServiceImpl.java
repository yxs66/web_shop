package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import com.yyy.springboot.exception.SQLInsertException;
import com.yyy.springboot.mapper.ProductSpecificationDetailMapper;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.service.ProductSpecificationDetailService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductSpecificationDetailServiceImpl implements ProductSpecificationDetailService {
    @Autowired
    private ProductSpecificationDetailMapper mapper;
    @Autowired
    private ProductService productService;

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
    @Transactional
    public void insertProductSpecificationDetail(ProductSpecificationDetail productSpecificationDetail) {
        Map<String, Object> map = new HashMap<>();
        map.put("ps_id", productSpecificationDetail.getProductSpecificationId());
        map.put("name", productSpecificationDetail.getName());
        ProductSpecificationDetail p = mapper.selectOne(new QueryWrapper<ProductSpecificationDetail>().allEq(map));
        if (p == null) {
            mapper.insert(productSpecificationDetail);
            count.incrementAndGet();
        }else{
            productSpecificationDetail.setId(p.getId());
        }
    }

    @Override
    public void isExistProductSpecificationDetail(Long productId,Collection<Long> keys,Collection<String> value){
        Integer count = mapper.selectCount(new QueryWrapper<ProductSpecificationDetail>().in("name", value).in("ps_id",keys));
        if (count == value.size()) {
            List<List<Long>> lists = productService.selectProductRepertoryMidPsdIdsByProductId(productId);
            System.out.println(lists);
            for (List<Long> ids : lists) {
                List<String> strings = new ArrayList<>();
                if(ids.size()!=0 &&ids!=null) {
                    strings = mapper.selectProductSpecificationDetailNameByPsdId(ids);
                }
                if (checkDifferent(value, strings))
                    throw new SQLInsertException(ResultUtil.repeatProductFail());
            }
        }
    }
    public boolean checkDifferent(Collection<String> list, Collection<String> list1) {
        if (list.size() != list1.size())
            return false;
        for (String str : list) {
            if (!list1.contains(str)) {
                return false;
            }
        }
        return true;
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
