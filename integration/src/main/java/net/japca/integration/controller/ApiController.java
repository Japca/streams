package net.japca.integration.controller;

import net.japca.integration.model.Item;
import net.japca.integration.service.IntegrationGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@RestController
public class ApiController {



    @Autowired
    private IntegrationGateway integrationGateway;

    @PostMapping("/")
    public String receive(@RequestBody Item item) {

        List<Item> items = LongStream.range(0, 10)
                .mapToObj(i -> new Item(i, item.getName() + i, item.getDescription() + i))
                .collect(Collectors.toList());

        integrationGateway.process(items);
        return "ok";
    }


}
