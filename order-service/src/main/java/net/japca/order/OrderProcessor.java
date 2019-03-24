package net.japca.order;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/18/19.
 */
public interface OrderProcessor {
    String INPUT = "input";
    String OUTPUT = "orderOut";

//    @Input
//    SubscribableChannel input();

    @Output
    MessageChannel orderOut();

}
