package net.japca.order.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/30/19.
 */

//@ImportResource("classpath:temp.xml")
@Configuration
public class RedisConfiguration {

    @Bean
    public MessageChannel orderReceivedChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel toRedisChannel() {
        return new DirectChannel();
    }

    @Transformer(inputChannel = "orderReceivedChannel", outputChannel = "toRedisChannel")
    @Bean
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer();
    }

    @ServiceActivator(inputChannel = "toRedisChannel")
    @Bean
    public RedisQueueOutboundChannelAdapter redisQueueOutboundChannelAdapter() {
        RedisQueueOutboundChannelAdapter queueOutboundChannelAdapter =
                new RedisQueueOutboundChannelAdapter("order-queue", new JedisConnectionFactory());
        queueOutboundChannelAdapter.setSerializer(new StringRedisSerializer());
        return  queueOutboundChannelAdapter;
    }
}
