package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.dto.ProductDetailNumDTO;
import com.yyy.springboot.entitys.*;
import com.yyy.springboot.exception.MySQLException;
import com.yyy.springboot.mapper.ProductMapper;
import com.yyy.springboot.service.*;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    private ProductTypeBrandMidService productTypeBrandMidService;
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
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        hashMap.put("type_id", productTypeId);
//        hashMap.put("brand_id", productBrandId);
//        return mapper.selectList(new QueryWrapper<Product>().allEq(hashMap, false)); //null2IsNull:为true则在map的value为null时调用sql的 is null ,为false时则忽略
        return mapper.selectProductByProductTypeIdAndProductBranId(productTypeId, productBrandId);
    }

    @Override
    public List<ProductVO> selectProductVoByProductUserId(Long userId) {
        return mapper.selectProductVoByProductUserId(userId);
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
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("user_id", product.getUserId());
        Product p = mapper.selectOne(new QueryWrapper<Product>().allEq(map));
        if (p == null) {
            mapper.insert(product);
        } else {
            throw new MySQLException(ResultUtil.repeatProductFail());
        }
    }

    @Transactional
    @Override
    public void insertProductDetailDTO(ProductDetailDTO productDetailDTO) {
        ProductType productType = new ProductType(null, productDetailDTO.getTypeName());
        productTypeService.insertProductType(productType);
        ProductBrand productBrand = new ProductBrand(null, productDetailDTO.getBrandName(), productDetailDTO.getBrandImage());
        productBrandService.insertProductBrand(productBrand);
        productTypeBrandMidService.insertProductTypeBrandMid(new ProductTypeBrandMid().setTypeId(productType.getId()).setBrandId(productBrand.getId()));
        Product product = new Product(null, productDetailDTO.getProductName(), productBrand.getId(), productType.getId(), productDetailDTO.getProductImage(), productDetailDTO.getUserId());
        this.insertProduct(product);
        List<ProductDetailNumDTO> productDetailNumDTOS = productDetailDTO.getProductDetailNumDTOS();
        if (CollectionUtils.isEmpty(productDetailNumDTOS))
            throw new MySQLException(ResultUtil.productParamIllegal());

        productDetailNumDTOS.forEach(productDetailNumDTO->{
            Map<String, String> map = productDetailNumDTO.getProductSpecificationDetail();
            if (CollectionUtils.isEmpty(map)) {
                if (productRepertoryMidService.selectProductRepertoryMidNullCountByProductId(product.getId()) > 0)
                    throw new MySQLException(ResultUtil.repeatProductFail());
            }
            ProductRepertory productRepertory = new ProductRepertory(null, product.getId(), productDetailNumDTO.getAmount(), productDetailNumDTO.getNum());
            productRepertoryService.insertProductRepertory(productRepertory);

            if (CollectionUtils.isEmpty(map))
                return;//这里本是continue 但因为是在forEach不能停止，所以return效果跟continue一样

            function(map, product.getId(), productRepertory.getId());
        });
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
        if (mapper.selectCount(new QueryWrapper<Product>().eq("id", productId).eq("user_id", productDetailDTO.getUserId())) == 0)
            throw new MySQLException(ResultUtil.illegalOperationInParam());
        List<ProductDetailNumDTO> productDetailNumDTOS = productDetailDTO.getProductDetailNumDTOS();
        if (CollectionUtils.isEmpty(productDetailNumDTOS))
            throw new MySQLException(ResultUtil.productParamIllegal());

        ProductDetailNumDTO productDetailNumDTO = productDetailNumDTOS.get(0);//只插入第一行

        Map<String, String> map = productDetailNumDTO.getProductSpecificationDetail();
        if (CollectionUtils.isEmpty(map)) {
            if (productRepertoryMidService.selectProductRepertoryMidNullCountByProductId(productId) > 0)
                throw new MySQLException(ResultUtil.repeatProductFail());
        }
        ProductRepertory productRepertory = new ProductRepertory(null, productId, productDetailNumDTO.getAmount(), productDetailNumDTO.getNum());
        productRepertoryService.insertProductRepertory(productRepertory);

        if (CollectionUtils.isEmpty(map))
            return;

        function(map, productId, productRepertory.getId());
    }

    @Override
    public ProductVO selectProductSpecificationVOByProductId(Long productId) {
        return mapper.selectProductSpecificationVOByProductId(productId);
    }

    public void function(Map<String, String> map, Long productId, Long productRepertoryId) {
        List<Long> keys = new ArrayList<>();

        map.entrySet().stream().map((entry) -> {
            ProductSpecification productSpecification = new ProductSpecification(null, productId, entry.getKey());
            productSpecificationService.insertProductSpecification(productSpecification);
            return productSpecification.getId();
        }).forEach(keys::add);

        ArrayList<String> values = new ArrayList<>(map.values());

        productSpecificationDetailService.isExistProductSpecificationDetail(productId, keys, values);

        for (int i = 0; i < values.size(); i++) {
            ProductSpecificationDetail productSpecificationDetail = new ProductSpecificationDetail(null, keys.get(i), values.get(i));
            productSpecificationDetailService.insertProductSpecificationDetail(productSpecificationDetail);
            ProductRepertoryMid productRepertoryMid = new ProductRepertoryMid(null, productRepertoryId, productSpecificationDetail.getId());
            productRepertoryMidService.insertProductRepertoryDetail(productRepertoryMid);
        }
    }
}
