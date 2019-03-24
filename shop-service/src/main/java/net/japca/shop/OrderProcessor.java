package net.japca.shop;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Jakub krhovják on 3/18/19.
 */
public interface OrderProcessor {

    public static final String ORDER_IN = "orderIn";


    @Input
    SubscribableChannel orderIn();

//    @Output("orderOut")
//    MessageChannel orderOut();

}
