package net.japca.shop.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Configuration
@EnableIntegration
@ConditionalOnProperty(value = "redis.enabled")
public class RedisConfiguration {

    @Bean
    public RedisConsumer redisConsumer() {
        return new RedisConsumer();
    }

    @Bean
    public MessageChannel eventChannel() {
        return new DirectChannel();
    }

    @Bean
    public RedisQueueMessageDrivenEndpoint redisInboundChannelAdapter() {
        RedisQueueMessageDrivenEndpoint redisInboundChannelAdapter = new RedisQueueMessageDrivenEndpoint("order-queue", new JedisConnectionFactory());
        redisInboundChannelAdapter.setSerializer(new StringRedisSerializer());
        redisInboundChannelAdapter.setOutputChannel(eventChannel());
        redisInboundChannelAdapter.setReceiveTimeout(0);
        return redisInboundChannelAdapter;
    }

    @ServiceActivator(inputChannel = "eventChannel")
    @Bean
    public ServiceActivatorFactoryBean redisConsumerFactoryBean() {
        ServiceActivatorFactoryBean serviceActivatorFactoryBean = new ServiceActivatorFactoryBean();
        serviceActivatorFactoryBean.setTargetObject(redisConsumer());
        serviceActivatorFactoryBean.setTargetMethodName("process");
        return serviceActivatorFactoryBean;
    }

}
