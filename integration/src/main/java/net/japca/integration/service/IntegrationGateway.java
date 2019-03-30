package net.japca.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by Jakub krhovják on 3/30/19.
 */

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "restInput.chanel")
    <T> void process(T object);
}
