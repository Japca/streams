package net.japca.order.ftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 4/4/19.
 */

@Slf4j
@Configuration
public class FtpConfiguration {

    @Bean
    public MessageChannel ftpChannel() {
        return new DirectChannel();
    }

    @Bean
    public DefaultFtpSessionFactory defaultFtpSessionFactory() {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        ftpSessionFactory.setHost("localhost");
        ftpSessionFactory.setPort(21);
        ftpSessionFactory.setUsername("testftp");
        ftpSessionFactory.setPassword("testftp");
        return ftpSessionFactory;
    }

    @Bean
    @ServiceActivator(inputChannel = "ftpChannel")
    public FtpMessageHandler ftpOutboundHandler() {
        FtpMessageHandler ftpOutboundHandler = new FtpMessageHandler(defaultFtpSessionFactory());
        ftpOutboundHandler.setRemoteDirectoryExpression(new LiteralExpression(""));
        ftpOutboundHandler.setFileNameGenerator(message -> String.valueOf(message.getHeaders().get("fileName")));
        return ftpOutboundHandler;
    }
}
