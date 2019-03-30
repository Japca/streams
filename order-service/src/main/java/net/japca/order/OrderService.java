package net.japca.order;

import net.japca.common.Order;
import net.japca.order.properties.OrderServiceProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(OrderServiceProperties.class)
@EnableBinding({OrderProcessor.class})
@EnableIntegration
public class OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }


    @StreamListener(OrderProcessor.ORDER_UPDATED)
    public void receiveUpdatedOrder(Order order) {
        log.info("Updated order received: {}", order);
    }

//    @Bean
    @InboundChannelAdapter(value = OrderProcessor.ORDER_OUT, poller = @Poller(fixedRate = "1000", maxMessagesPerPoll = "1"))
    public MessageSource<Order> ordersSource() {
        Random random = new Random();
        return () -> new GenericMessage<>(new Order("new order " +  random.nextInt(100), random.nextInt(10)));
    }

}
