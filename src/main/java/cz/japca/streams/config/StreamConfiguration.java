package cz.japca.streams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import cz.japca.streams.client.Client;
import cz.japca.streams.listener.Listener;
import cz.japca.streams.processor.MessageProcessor;

/**
 * Created by Jakub krhovják on 3/17/19.
 */
@Configuration
@EnableBinding(MessageProcessor.class)
public class StreamConfiguration {

    @Autowired
    BindingServiceProperties BindingServiceProperties;

    @PostConstruct
    public void init() {
        BindingServiceProperties.setDefaultBinder("rabbit");
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public Listener listener() {
        return new Listener();
    }

//    @Bean
//    public BinderFactory binderFactory(BinderTypeRegistry binderTypeRegistry,
//                                       BindingServiceProperties bindingServiceProperties) {
//
//        DefaultBinderFactory binderFactory = new DefaultBinderFactory(
//                getBinderConfigurations(binderTypeRegistry, bindingServiceProperties), binderTypeRegistry);
//        binderFactory.setDefaultBinder(bindingServiceProperties.getDefaultBinder());
//        binderFactory.setListeners(binderFactoryListeners);
//        return binderFactory;
//    }

}
