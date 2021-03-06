package net.japca.shop.redis;

import net.japca.common.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Slf4j
public class RedisConsumer {

    public Order process(Order order) {
        log.info("Order received from redis: {}", order);
        return order;
    }

}
