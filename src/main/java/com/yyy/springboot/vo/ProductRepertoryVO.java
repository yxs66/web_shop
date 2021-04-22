package com.yyy.springboot.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/18 1:55
 * @Version 1.0
 **/
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRepertoryVO {
    @TableId("pr_id")
    private Long id;
    private BigDecimal amount;
    private Integer num;
    private List<String> specificationNames;
}
