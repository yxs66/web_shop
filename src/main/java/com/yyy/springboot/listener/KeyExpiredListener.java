package com.yyy.springboot.listener;

import com.yyy.springboot.entitys.ProductRepertory;
import com.yyy.springboot.entitys.RepertoryDifference;
import com.yyy.springboot.service.ProductRepertoryService;
import com.yyy.springboot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//监听过期的key
@Component
@Slf4j
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Value("${order_prefix}")
    private final String orderPrefix = "order_";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProductRepertoryService productRepertoryService;

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    @Transactional
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        if (key.startsWith(orderPrefix)) {
            log.info("监听器执行->当前过期的订单ID:" + key);
            List<RepertoryDifference> repertoryDifferences = (ArrayList<RepertoryDifference>) redisUtil.getDel(key + "value");
            // 回滚库存
            repertoryDifferences.forEach(x->{
                productRepertoryService.addProductRepertoryNumById(x.getProductRepertoryId(), x.getNum());
            });

        }
    }
}
