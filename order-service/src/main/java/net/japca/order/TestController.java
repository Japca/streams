package net.japca.order;

import net.japca.common.Order;
import net.japca.order.Rabbit.OrderProcessor;
import net.japca.order.redis.RedisGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private RedisGateway redisGateway;

    @GetMapping("/stream")
    public String sendStream() {
        orderProcessor.orderOut().send((MessageBuilder
                .withPayload(new Order("Order stream", 3))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()));

        return "ok";
    }

    @GetMapping("/redis")
    public String sendRedis() {
        Order order = new Order("Order Redis", 1);
        redisGateway.publish(new Order("Order Redis", 1));
        log.info("Published to redis: {}", order);
        return "ok";
    }

}
