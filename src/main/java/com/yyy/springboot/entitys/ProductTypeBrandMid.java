package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/22 16:11
 * @Version 1.0
 **/
@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_type_brand_mid")
@Accessors(chain = true)
public class ProductTypeBrandMid {

    @TableId(type = IdType.AUTO)
    private Integer id;//品牌分类中间表编号

    @TableField("type_id")
    @NotNull(message = "ProductTypeBrandMid.typeId.null")
    private Integer typeId;//分类编号

    @TableField("brand_id")
    @NotNull(message = "ProductTypeBrandMid.brandId.null")
    private Integer brandId;//品牌编号


}
