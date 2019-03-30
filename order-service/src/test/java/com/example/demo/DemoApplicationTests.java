package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import net.japca.order.OrderService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderService.class)
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    protected MockMvc mvc;

    @Test
    public void getBenefitProgramType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/redis"))
                .andExpect(content().string("ok"));

    }

}
