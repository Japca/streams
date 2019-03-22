package com.japca.order;

import com.japca.common.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jakub krhovják on 3/22/19.
 */

@RestController
//@RequestMapping("/")
public class OrderController {

    @Autowired
    private MessageChannel orderOut;

    @GetMapping("/")
    public String sendOrder() {
        orderOut.send((MessageBuilder
                .withPayload(new Order("Test order", 22))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build()));
        return "ok";
    }

}
