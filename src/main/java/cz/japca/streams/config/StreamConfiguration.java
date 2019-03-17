package cz.japca.streams.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cz.japca.streams.client.Client;
import cz.japca.streams.listener.Listener;

/**
 * Created by Jakub krhovják on 3/17/19.
 */
@Configuration
//@EnableBinding(MessageProcessor.class)
@EnableBinding(Processor.class)
public class StreamConfiguration {

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public Listener listener() {
        return new Listener();
    }

}
