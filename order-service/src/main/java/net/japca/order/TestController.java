package net.japca.order;

import net.japca.common.Order;
import net.japca.order.Rabbit.OrderProcessor;
import net.japca.order.redis.RedisGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
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
    public  String voidsendStream(@RequestParam Integer count) {
        count = count == null ?  new Random().nextInt(10) : count;
        orderProcessor.orderOut().send((MessageBuilder
                .withPayload(new Order("Order stream", count))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()));

        return "ok";
    }

    @GetMapping("/redis")
    @InboundChannelAdapter(value = "orderReceivedChannel", poller = @Poller(fixedRate = "2000"))
    public Order sendRedis() {
        Order order = new Order("Order redis Gateway",  new Random().nextInt(100));
        redisGateway.publish(new Order("Order redis InboundChannel",  new Random().nextInt(100)));
        log.info("Published to redis: {}", order);
        return order;
    }

}
