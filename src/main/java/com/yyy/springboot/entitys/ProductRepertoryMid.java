package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_repertory_mid")
public class ProductRepertoryMid {

    @TableId(type = IdType.AUTO)
    private Long id;//商品库存关联表编号

    @TableField("product_repertory_id")
    @NotNull(message = "ProductRepertoryDetail.productRepertoryId.null")
    private Long productRepertoryId;//商品库存编号

    @TableField("psd_id")
    @NotNull(message = "ProductRepertoryDetail.psdId.null")
    private Long psdId;//详细规格编号
}
