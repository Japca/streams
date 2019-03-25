package net.japca.streams.listener;

import net.japca.common.Order;

import net.japca.streams.processor.MessageProcessor;

import org.springframework.cloud.stream.annotation.StreamListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/17/19.
 */
@Slf4j
public class Listener {

 //   @StreamListener(MessageProcessor.INPUT)
//    @SendTo(MessageProcessor.OUTPUT)

    @StreamListener(MessageProcessor.INPUT)
    public void sendDataMessage(Order dataMessage) {
        log.info("Message Received: {}", dataMessage);
    }

}
