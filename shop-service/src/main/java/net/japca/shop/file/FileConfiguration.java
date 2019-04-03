package net.japca.shop.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 4/3/19.
 */
@Configuration
public class FileConfiguration {

    @Bean
    public MessageChannel printChannel() {
        return new DirectChannel();
    }

    @Bean
    public RedisQueueMessageDrivenEndpoint redisFileInboundAdapter() {
        RedisQueueMessageDrivenEndpoint redisInboundChannelAdapter = new RedisQueueMessageDrivenEndpoint("file-queue", new JedisConnectionFactory());
        redisInboundChannelAdapter.setSerializer(new StringRedisSerializer());
        redisInboundChannelAdapter.setOutputChannel(printChannel());
        redisInboundChannelAdapter.setReceiveTimeout(0);
        return redisInboundChannelAdapter;
    }


    @ServiceActivator(inputChannel = "printChannel")
    @Bean
    public ServiceActivatorFactoryBean printServiceActivator() {
        ServiceActivatorFactoryBean serviceActivatorFactoryBean = new ServiceActivatorFactoryBean();
        serviceActivatorFactoryBean.setTargetObject(new FileService());
        serviceActivatorFactoryBean.setTargetMethodName("print");
        return serviceActivatorFactoryBean;
    }
}
