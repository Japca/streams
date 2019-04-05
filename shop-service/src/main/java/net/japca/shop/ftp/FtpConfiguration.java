package net.japca.shop.ftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizer;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizingMessageSource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Created by Jakub krhovják on 4/4/19.
 */

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
    @InboundChannelAdapter(channel = "ftpChannel", poller = @Poller(fixedDelay = "1000"))
    public FtpInboundFileSynchronizingMessageSource ftpInboundAdapter() {
        FtpInboundFileSynchronizer ftpInboundFileSynchronizer = new FtpInboundFileSynchronizer(defaultFtpSessionFactory());
       ftpInboundFileSynchronizer.setRemoteDirectoryExpression(new LiteralExpression(""));

        FtpInboundFileSynchronizingMessageSource ftpInboundAdapter =
                new FtpInboundFileSynchronizingMessageSource(ftpInboundFileSynchronizer);
        ftpInboundAdapter.setLocalDirectory(new File("shop-service/src/main/resources/from-ftp"));
        return ftpInboundAdapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "ftpChannel")
    public ServiceActivatorFactoryBean ftpPrintServiceActivator() {
        ServiceActivatorFactoryBean serviceActivatorFactoryBean = new ServiceActivatorFactoryBean();
        serviceActivatorFactoryBean.setTargetObject(new FtpService());
        serviceActivatorFactoryBean.setTargetMethodName("print");
        return serviceActivatorFactoryBean;
    }

}
