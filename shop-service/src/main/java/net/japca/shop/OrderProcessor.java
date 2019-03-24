package net.japca.shop;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Jakub krhovják on 3/18/19.
 */
public interface OrderProcessor {

    public static final String INPUT = "input";
//
//    @Input(OrderProcessor.FROM_ORDER_SERVICE)

    @Input
    SubscribableChannel input();
    @Output("orderOut")
    MessageChannel orderOut();

}
