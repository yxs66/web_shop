package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.OrderDetail;
import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.vo.OrderVO;
import com.yyy.springboot.vo.SettlementProductVO;
import com.yyy.springboot.vo.SettlementVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMasterMapper extends BaseMapper<OrderMaster> {

    @Select("<script>" +
            "select p.id productId,p.name productName,p.image product_image,pr.amount product_amount from product p " +
            "left join product_repertory pr on p.id= pr.product_id " +
            "left join product_repertory_mid prm on pr.id=prm.product_repertory_id " +
            "where  p.id=#{productId} and prm.psd_id in" +
            "<foreach collection='psdIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "group by pr.id having count(pr.id)=#{psdIdsLength}" +
            "</script>"
    )
    OrderDetail selectOrderDetailByPsdIds(@Param("productId") Long productId, @Param("psdIds") List<Long> psdIds, @Param("psdIdsLength")Integer psdIdsLength);

    @Select("select status from order_master where id=#{orderId}")
    Byte selectOrderStatusByOrderId(@Param("orderId") Long orderId);

    List<OrderVO> selectOrderVOByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Byte status);

    List<OrderVO> selectOrderVOByUserId(@Param("userId") Long userId);
}
