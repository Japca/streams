package net.japca.order;

import net.japca.order.properties.OrderServiceProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.integration.config.EnableIntegration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(OrderServiceProperties.class)
@EnableIntegration
public class OrderService {

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }



}
