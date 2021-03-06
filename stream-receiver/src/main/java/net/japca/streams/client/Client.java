package net.japca.streams.client;

import net.japca.common.DataMessage;

import net.japca.streams.processor.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by Jakub krhovják on 3/17/19.
 */

public class Client {


    @Autowired
    private MessageProcessor processor;

//    @SendTo(Processor.OUTPUT)
    public boolean send(DataMessage dataMessage) {
        MessageChannel messageChannel = processor.input();
        return messageChannel.send(MessageBuilder
                .withPayload(dataMessage)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
