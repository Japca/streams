package net.japca.shop;

import net.japca.common.Order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class ShopService {

	public static void main(String[] args) {
		SpringApplication.run(ShopService.class, args);
	}


	@StreamListener(OrderProcessor.ORDER_SEND)
	@SendTo(OrderProcessor.ORDER_OUT)
	public Order receiverOrder(Order order) {
		log.info("order received: {}", order);
		order.setDescription(" Description changed");
		return order;
	}

//	@ServiceActivator(inputChannel = OrderProcessor.ORDER_SEND + ".shop-service.errors")
//	public void error(Order message) {
//		System.out.println("Handling ERROR: " + message);
//	}

}
