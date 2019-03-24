package net.japca.streams.Controller;

import com.japca.common.DataMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.japca.streams.client.Client;

/**
 * Created by Jakub krhovják on 3/17/19.
 */

@RestController
public class TestController {

    @Autowired
    private Client client;

    @GetMapping("/")
    public String send() {
        client.send(new DataMessage(1L, "Test Mesasge"));
        return "ok";
    }

}
