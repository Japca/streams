package com.japca.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Jakub krhovják on 3/18/19.
 */
public interface OrderProcessor {
    public  String ORDER_IN = "orderIn";


    @Input("orderIn")
    SubscribableChannel orderIn();
    @Output("orderOut")
    MessageChannel orderOut();

}
