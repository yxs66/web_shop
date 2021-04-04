package com.yyy.springboot.dto;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Long id;
    @Length(min = 1,max = 20,message = "ProductDetailDTO.productName长度需要在1和20之间")
    @NotBlank(message = "ProductDetailDTO.productName.null")
    private String productName;//商品名称
    @NotBlank(message = "ProductDetailDTO.productImage.null")
    private String productImage;//商品图片
    private String typeName;//商品类型名称
    private String brandName;//商品品牌名称
    private String brandImage;//商品品牌图片

    private Map<String,String> productSpecificationDetail;//商品详细规格 {颜色：白色}

    private Integer amount;//商品价格
    private Integer num;//商品数量


}
