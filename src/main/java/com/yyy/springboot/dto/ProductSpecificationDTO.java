package com.yyy.springboot.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class ProductSpecificationDTO {

    @TableId(type = IdType.AUTO)
    private Long id;//规格编号

    @TableField("product_id")
    @NotNull(message = "ProductSpecification.productId.null")
    private Long productId;//商品编号

    @Length(min = 1,max = 20,message = "Product.name长度需要在1和20之间")
    @NotBlank(message = "ProductSpecification.name.null")
    private String name;//规格名称

    private List<ProductSpecificationDetailDTO> productSpecificationDetailDTOS;
}
