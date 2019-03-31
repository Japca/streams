package net.japca.shop.redis;

import net.japca.common.Order;

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
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Configuration
@EnableIntegration
@ConditionalOnProperty(value = "redis.enabled")
//@ImportResource("classpath:temp.xml")
public class RedisConfiguration {

    @Bean
    public MessageChannel fromRedisChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel transformChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel eventChannel() {
        return new DirectChannel();
    }

    @Bean
    public StringRedisSerializer serializer() {
        return new StringRedisSerializer();
    }

    @InboundChannelAdapter(channel = "fromRedisChannel", poller = @Poller(fixedRate = "10000",  maxMessagesPerPoll = "1"))
    @Bean
    public RedisQueueMessageDrivenEndpoint redisInboundChannelAdapter() {
        RedisQueueMessageDrivenEndpoint redisInboundChannelAdapter = new RedisQueueMessageDrivenEndpoint("order-queue", new JedisConnectionFactory());
        redisInboundChannelAdapter.setSerializer(serializer());
        redisInboundChannelAdapter.setOutputChannel(transformChanel());
        return redisInboundChannelAdapter;
    }


    @Transformer(inputChannel = "transformChanel", outputChannel = "eventChannel")
    @Bean
    public JsonToObjectTransformer objectToJsonTransformer() {
        return new JsonToObjectTransformer(Order.class);
    }

    @ServiceActivator(inputChannel = "eventChannel")
    @Bean
    public ServiceActivatorFactoryBean redisConsumer() {
        ServiceActivatorFactoryBean serviceActivatorFactoryBean = new ServiceActivatorFactoryBean();
        serviceActivatorFactoryBean.setTargetObject(new RedisConsumer());
        serviceActivatorFactoryBean.setTargetMethodName("process");
        return serviceActivatorFactoryBean;
    }

}
