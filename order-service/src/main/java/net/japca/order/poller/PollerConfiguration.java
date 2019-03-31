package net.japca.order.poller;

import net.japca.common.Order;
import net.japca.order.Rabbit.OrderProcessor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;

/**
 * Created by Jakub krhovják on 3/31/19.
 */
@Configuration
@ConditionalOnProperty("polling.enabled")
public class PollerConfiguration {

    @Bean
    @InboundChannelAdapter(value = OrderProcessor.ORDER_OUT, poller = @Poller(fixedRate = "1000"))
    public MessageSource<Order> rabbitSource() {
        return () -> new GenericMessage<>(new Order("Order Rabbit " , new Random().nextInt(100)));
    }

    @Bean
    @InboundChannelAdapter(value = "orderReceivedChannel", poller = @Poller(fixedRate = "2000"))
    public MessageSource<Order> redisSource() {
        return () -> new GenericMessage<>(new Order("Order Redis " , new Random().nextInt(100)));
    }



}
