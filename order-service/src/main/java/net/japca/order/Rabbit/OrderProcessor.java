package net.japca.order.Rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Jakub krhovják on 3/18/19.
 */
public interface OrderProcessor {
    String ORDER_UPDATED = "orderUpdated";
    String ORDER_OUT = "orderOut";

    @Input
    SubscribableChannel orderUpdated();

    @Output
    MessageChannel orderOut();

}
