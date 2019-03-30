package net.japca.order.Rabbit;

import net.japca.common.Order;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
//

@Slf4j
@Configuration
@EnableBinding({OrderProcessor.class})
public class RabbitConfiguration {

    @Bean
    @InboundChannelAdapter(value = OrderProcessor.ORDER_OUT, poller = @Poller(fixedRate = "1000", maxMessagesPerPoll = "1"))
    public MessageSource<Order> ordersSource() {
        Random random = new Random();
        return () -> new GenericMessage<>(new Order("Order rabbit " +  random.nextInt(100), random.nextInt(10)));
    }

    @StreamListener(OrderProcessor.ORDER_UPDATED)
    public void receiveUpdatedOrder(Order order) {
        log.info("Updated order received: {}", order);
    }
}
