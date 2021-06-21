package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Deacription 购物车
 * @Author yxs
 * @Date 2021/5/4 16:49
 * @Version 1.0
 **/
@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
public class Cart{
    private Long productId; // 商品id
    private String productName; //商品名称
    private List<Long> specificationId; // 规格Id
    private List<String> specificationName; // 规格名称
    private BigDecimal amount;// 商品价格
    private String image;//商品图片
    private Integer num; //商品数量
}
