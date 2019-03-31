package net.japca.order.redis;

import net.japca.common.Order;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@MessagingGateway
public interface RedisGateway {

    @Gateway(requestChannel = "toRedisChannel")
    void publish(Order order);

}
