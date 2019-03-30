package net.japca.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.RecipientListRouter;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Configuration
@EnableIntegration
public class IntegrationConfiguration {

    @ServiceActivator(inputChannel = "restInput.chanel")
    @Bean
    public RecipientListRouter recepientListRouter() {
        RecipientListRouter recipientListRouter = new RecipientListRouter();
        recipientListRouter.addRecipient("itemService.channel");
        return recipientListRouter;
    }

}
