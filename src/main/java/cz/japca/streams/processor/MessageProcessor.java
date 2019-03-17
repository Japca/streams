package cz.japca.streams.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.SubscribableChannel;

@Profile("rabbit")
public interface MessageProcessor {
    String INPUT = "messageInput";
//    String OUTPUT = "output";

    @Input(INPUT)
    SubscribableChannel input();
//
//    @Output(OUTPUT)
//    MessageChannel output();

//    @Output
//    MessageChannel output2();
}
