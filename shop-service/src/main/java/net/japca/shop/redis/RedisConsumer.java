package net.japca.shop.redis;

import net.japca.common.Order;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Slf4j
@Service("RedisConsumer")
public class RedisConsumer {

    public void process(Order order) {
        log.info("Order received from redis: {}", order);
    }

}
