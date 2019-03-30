package net.japca.shop.rabiit;

import net.japca.common.Order;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Slf4j
@Configuration
@EnableBinding(OrderProcessor.class)
public class RabbitConfiguration {

    @StreamListener(OrderProcessor.ORDER_SEND)
    @SendTo(OrderProcessor.ORDER_OUT)
    public Order receiverOrder(Order order) {
        log.info("order received: {}", order);
        order.setDescription(" Description changed");
        if(order.getCount() == 3) {
            throw new RuntimeException("Exception");
        }
        return order;
    }

    @ServiceActivator(inputChannel = OrderProcessor.ORDER_SEND + ".shop-service.errors")
    public void error(Message<?> message) {
        log.error("Handling ERROR: " + message);
    }


}
