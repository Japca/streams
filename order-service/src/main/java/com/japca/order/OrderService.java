package com.japca.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding({OrderProcessor.class})
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}

}
