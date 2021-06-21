package com.yyy.springboot.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyy.springboot.entitys.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/5/6 14:27
 * @Version 1.0
 **/
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    private Long id;//订单编号

    private Byte status;//订单状态  0:待支付 1：已支付 2：已过期 3：已退款 4：待收货 5：已完成

    private Integer productTotal;//商品件数

    private BigDecimal productTotalAmount;//商品总价格

    private BigDecimal discount;//优惠

    private BigDecimal carriage;//运费

    private BigDecimal amount;//支付金额

    private Long userAddressId;//用户地址

    private List<SettlementProductVO> settlementProductVOS; //商品信息

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; // 订单创建时间

}
