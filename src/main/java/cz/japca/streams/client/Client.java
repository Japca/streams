package cz.japca.streams.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import cz.japca.streams.model.DataMessage;

/**
 * Created by Jakub krhovják on 3/17/19.
 */

public class Client {

    @Autowired
    private Processor processor;

//    @SendTo(Processor.OUTPUT)
    public boolean send(DataMessage dataMessage) {
        MessageChannel messageChannel = processor.input();
        return messageChannel.send(MessageBuilder
                .withPayload(dataMessage)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
