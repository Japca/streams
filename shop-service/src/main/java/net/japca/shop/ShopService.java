package net.japca.shop;

import net.japca.common.Order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class ShopService {

	public static void main(String[] args) {
		SpringApplication.run(ShopService.class, args);
	}


	@StreamListener(OrderProcessor.ORDER_IN)
	public void receiverOrder(Order order) {
		log.info("order received: {}", order);
	}

}
