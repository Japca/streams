package net.japca.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Data
@ConfigurationProperties(prefix = "order")
public class OrderServiceProperties {

    Redis redis = new Redis();

    @Data
    public class Redis {
        String host;
        int port;
    }

}
