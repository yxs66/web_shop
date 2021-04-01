package com.yyy.springboot.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//商品编号

    @Length(min = 1,max = 20,message = "Product.name长度需要在1和20之间")
    @NotBlank(message = "Product.name.null")
    private String name;//商品名称

    @TableField("brand_id")
    @NotNull(message = "Product.brandId.null")
    private Integer brandId;//品牌编号

    @TableField("type_id")
    @NotNull(message = "Product.typeId.null")
    private Integer typeId;//类型编号

    @NotBlank(message = "Product.image.null")
    private String image;

    private List<ProductSpecificationDTO> productSpecificationDTOS;
}
