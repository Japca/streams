package net.japca.integration.controller;

import net.japca.integration.model.Item;
import net.japca.integration.service.IntegrationGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@RestController
public class ApiController {

    @Autowired
    private IntegrationGateway integrationGateway;

    @PostMapping("/")
    public String receive(@RequestBody Item item) {
        integrationGateway.process(item);
        return "ok";
    }

}
