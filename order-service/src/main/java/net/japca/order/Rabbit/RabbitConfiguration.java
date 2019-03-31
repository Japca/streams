package net.japca.order.Rabbit;

import net.japca.common.Order;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
//

@Slf4j
@Configuration
@EnableBinding({OrderProcessor.class})
public class RabbitConfiguration {

    @StreamListener(OrderProcessor.ORDER_UPDATED)
    public void receiveUpdatedOrder(Order order) {
        log.info("Updated order received: {}", order);
    }
}
