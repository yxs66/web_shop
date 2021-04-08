package com.yyy.springboot.dto;

import com.yyy.springboot.config.Insert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    @Length(min = 1,max = 20,message = "ProductDetailDTO.productName长度需要在1和20之间",groups = Insert.class)
    @NotBlank(message = "ProductDetailDTO.productName.null",groups = Insert.class)
    private String productName;//商品名称
    @NotBlank(message = "ProductDetailDTO.productImage.null",groups = Insert.class)
    private String productImage;//商品图片
    @NotNull(message = "ProductDetailDTO.userId.null",groups = {Insert.class, Default.class})
    private Long userId;
    private String typeName;//商品类型名称
    private String brandName;//商品品牌名称
    private String brandImage;//商品品牌图片

    List<ProductDetailNumDTO> productDetailNumDTOS;

}
