package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_repertory_detail")
public class ProductRepertoryDetail {
    @TableField("product_repertory_id")
    private Long productRepertoryId;//商品库存详情编号
    @TableField("psd_id")
    private String psdId;//详细规格编号
}
