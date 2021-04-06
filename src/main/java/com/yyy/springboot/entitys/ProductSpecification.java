package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_specification")
public class ProductSpecification {

    @TableId(type = IdType.AUTO)
    private Long id;//规格编号

    @TableField("product_id")
    @NotNull(message = "ProductSpecification.productId.null")
    private Long productId;//商品编号

    @Length(min = 1,max = 20,message = "Product.name长度需要在1和20之间")
    @NotBlank(message = "ProductSpecification.name.null")
    private String name;//规格名称

    public ProductSpecification(Long id,Long productId,String name) {
        this.id = id;
        this.productId = productId;
        this.name = name;
    }

    @TableField(exist = false)  //表示数据库不存在该字段
    @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
    private List<ProductSpecificationDetail> productSpecificationDetails;

}
