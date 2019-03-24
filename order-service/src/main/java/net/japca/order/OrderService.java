package net.japca.order;

import net.japca.common.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RestController
@EnableBinding({OrderProcessor.class})
public class OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }

    @Autowired
    private OrderProcessor orderProcessor;

    @GetMapping("/")
    public String sendOrder() {
        orderProcessor.orderOut().send((MessageBuilder
                .withPayload(new Order("Order test", 1))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()));

        return "ok";
    }

    @StreamListener(OrderProcessor.ORDER_UPDATED)
    public void receiveUpdatedOrder(Order order) {
        log.info("Updated order received: {}", order);
    }

}
