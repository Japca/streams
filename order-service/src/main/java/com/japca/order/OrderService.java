package com.japca.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding({OrderProcessor.class})
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}

	@Autowired
	private MessageChannel orderOut;

	@GetMapping("/")
	public void sendOrder() {
		orderOut.send((MessageBuilder
				.withPayload("test message")
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build()));
	}
}
