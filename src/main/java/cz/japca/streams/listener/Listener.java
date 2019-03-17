package cz.japca.streams.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import cz.japca.streams.model.DataMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/17/19.
 */
@Slf4j
public class Listener {

 //   @StreamListener(MessageProcessor.INPUT)
//    @SendTo(MessageProcessor.OUTPUT)

    @StreamListener(Sink.INPUT)
    public void sendDataMessage(@Payload DataMessage dataMessage) {
        log.info("Message Received: {}", dataMessage);
    }

}
