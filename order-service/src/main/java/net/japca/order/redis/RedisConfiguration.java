package net.japca.order.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/30/19.
 */

@Configuration
public class RedisConfiguration {

    @Bean
    public MessageChannel toRedisChannel() {
        return new DirectChannel();
    }


    @ServiceActivator(inputChannel = "toRedisChannel")
    @Bean
    public RedisQueueOutboundChannelAdapter redisQueueOutboundChannelAdapter() {
        RedisQueueOutboundChannelAdapter queueOutboundChannelAdapter =
                new RedisQueueOutboundChannelAdapter("order-queue", new JedisConnectionFactory());
        queueOutboundChannelAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return  queueOutboundChannelAdapter;
    }

}
