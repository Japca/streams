package net.japca.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by Jakub krhovják on 3/30/19.
 */

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "restInput.chanel", headers = {@GatewayHeader( name="secretKey", value ="tajny" )} )
    <T> void process(T object);
}
