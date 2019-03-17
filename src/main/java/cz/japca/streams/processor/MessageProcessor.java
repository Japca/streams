package cz.japca.streams.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageProcessor {
    String INPUT = "input";
    String OUTPUT = "output";

    @Input(INPUT)
    SubscribableChannel messageInput();

    @Output(OUTPUT)
    MessageChannel output();

//    @Output
//    MessageChannel output2();
}
