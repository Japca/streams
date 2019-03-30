package net.japca.order.redis;

import net.japca.common.Order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Random;

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


    @InboundChannelAdapter(value = "orderReceivedChannel", poller = @Poller(fixedRate = "1000", maxMessagesPerPoll = "1"))
    @Bean
    public MessageSource<Order> ordersSource() {
        Random random = new Random();
        return () -> new GenericMessage<>(new Order("Order redis" +  random.nextInt(100), random.nextInt(10)));
    }

}
