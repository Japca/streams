package net.japca.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableIntegration
public class ShopService {

	public static void main(String[] args) {
		SpringApplication.run(ShopService.class, args);
	}



}
