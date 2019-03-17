package cz.japca.streams.listener;

import org.springframework.cloud.stream.annotation.StreamListener;

import cz.japca.streams.model.DataMessage;
import cz.japca.streams.processor.MessageProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/17/19.
 */
@Slf4j
public class Listener {

 //   @StreamListener(MessageProcessor.INPUT)
//    @SendTo(MessageProcessor.OUTPUT)

    @StreamListener(MessageProcessor.INPUT)
    public void sendDataMessage(DataMessage dataMessage) {
        log.info("Message Received: {}", dataMessage);
    }

}
