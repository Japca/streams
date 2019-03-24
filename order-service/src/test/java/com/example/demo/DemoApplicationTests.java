package com.example.demo;

import net.japca.order.OrderService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderService.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
