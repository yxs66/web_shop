package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNullFields;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {
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

    @TableField("user_id")
    @NotNull(message = "Product.userId.null")
    @JsonIgnore
    private Long userId;

    public Product(Long id,String name,Integer brandId, Integer typeId, String image, Long userId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.typeId = typeId;
        this.image = image;
        this.userId = userId;
    }

    @TableField(select = false)  //是否进行 select 查询
    @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
    private Long prId;//商品库存id

    @TableField(select = false)  //是否进行 select 查询
    @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
    private BigDecimal amount;//商品金额

    @TableField(select = false)  //是否进行 select 查询
    @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
    private Integer num;//商品数量
}
