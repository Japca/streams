package net.japca.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Service
@Slf4j
public class ItemService {

//    @Filter(inputChannel = "filter.channel", outputChannel = "itemService.channel")


    @ServiceActivator(inputChannel = "itemService.channel")
    public void processItem(Message<?> message) {
        log.info("Item processed: {}", message);
        message.getHeaders().forEach((key, value) -> {
            log.info("{} : {}", key, value);
        });

    }

}
