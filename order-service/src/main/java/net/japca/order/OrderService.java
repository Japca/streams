package net.japca.order;

import net.japca.order.ftp.FtpOutGateway;
import net.japca.order.properties.OrderServiceProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.integration.config.EnableIntegration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(OrderServiceProperties.class)
@EnableIntegration
public class OrderService implements ApplicationRunner {

    @Autowired
    private FtpOutGateway ftpOutGateway;

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ftpOutGateway.put("test.txt" , "Just test message");
    }
}
