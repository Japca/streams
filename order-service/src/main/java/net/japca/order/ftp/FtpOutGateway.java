package net.japca.order.ftp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * Created by Jakub krhovják on 4/4/19.
 */

@MessagingGateway
public interface FtpOutGateway {

    @Gateway(requestChannel = "ftpChannel")
    void put(@Header("fileName") String fileName, @Payload String message);
}
