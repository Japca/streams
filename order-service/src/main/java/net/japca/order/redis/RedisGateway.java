package net.japca.order.redis;

import net.japca.common.Order;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
public interface RedisGateway {

    void publish(Order order);

}
