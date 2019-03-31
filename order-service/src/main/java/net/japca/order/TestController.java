package net.japca.order;

import net.japca.common.Order;
import net.japca.order.Rabbit.OrderProcessor;
import net.japca.order.redis.RedisGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Slf4j
@RestController
public class TestController {

    private Random random = new Random();

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private RedisGateway redisGateway;

    @GetMapping("/rabbit")
    public  String sendStream(@RequestParam Integer count) {
        count = count == null ?  new Random().nextInt(100) : count;
        Order order = new Order("Order stream", count);
        orderProcessor.orderOut().send((MessageBuilder
                .withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()));

        log.info("Published to Rabbit: {}", order);

        return "ok";
    }

    @GetMapping("/redis")
    public String sendRedis() {
        Order order = new Order("Order redis Gateway",  new Random().nextInt(100));
        redisGateway.publish(order);
        log.info("Published to Redis: {}", order);

        return "ok";
    }

}
