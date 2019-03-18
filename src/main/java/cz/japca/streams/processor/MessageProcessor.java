package cz.japca.streams.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Profile("rabbit")
public interface MessageProcessor {
    String INPUT = "input";
    String OUTPUT = "output";

    @Input
    SubscribableChannel input();

    @Output
    MessageChannel output();

}
