package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_specification_detail")
public class ProductSpecificationDetail {
    @TableId(type = IdType.AUTO)
    private Long id;//详细规格编号

    @TableField("ps_id")
    @NotNull(message = "ProductSpecificationDetail.productSpecificationId.null")
    private Long productSpecificationId;//规格编号

    @Length(min = 1,max = 20,message = "ProductSpecificationDetail.name长度需要在1和20之间")
    @NotBlank(message = "ProductSpecificationDetail.name.null")
    private String name;//详细规格名称

}
