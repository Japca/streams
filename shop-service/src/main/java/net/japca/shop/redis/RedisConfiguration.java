package net.japca.shop.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.redis.inbound.RedisInboundChannelAdapter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Configuration
@ConditionalOnProperty(value = "redis.enabled", matchIfMissing = false)
public class RedisConfiguration {

    @Bean
    public MessageChannel fromRedisChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel transformChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel eventChannel() {
        return new QueueChannel();
    }

    @InboundChannelAdapter(channel = "fromRedisChannel" )
    @Bean
    public RedisInboundChannelAdapter redisInboundChannelAdapter() {
        RedisInboundChannelAdapter redisInboundChannelAdapter = new RedisInboundChannelAdapter(new JedisConnectionFactory());
        redisInboundChannelAdapter.setSerializer(new StringRedisSerializer());
        redisInboundChannelAdapter.setTopics("order-queue");
        redisInboundChannelAdapter.setOutputChannel(transformChanel());
        return redisInboundChannelAdapter;
    }

    @Transformer(inputChannel = "transformChanel", outputChannel = "eventChannel")
    @Bean
    public JsonToObjectTransformer objectToJsonTransformer() {
        return new JsonToObjectTransformer();
    }

    @ServiceActivator(inputChannel = "eventChannel", poller = @Poller(fixedRate = "2000", maxMessagesPerPoll = "1"))
    @Bean
    public ServiceActivatorFactoryBean redisConsumer() {
        ServiceActivatorFactoryBean serviceActivatorFactoryBean = new ServiceActivatorFactoryBean();
        serviceActivatorFactoryBean.setTargetObject(new RedisConsumer());
        serviceActivatorFactoryBean.setTargetMethodName("process");
        return serviceActivatorFactoryBean;
    }



}
