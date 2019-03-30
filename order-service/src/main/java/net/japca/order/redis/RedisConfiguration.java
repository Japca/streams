package net.japca.order.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Jakub krhovják on 3/30/19.
 */

@ImportResource("classpath:temp.xml")
//@AutoConfigureAfter(RedisConfig.class)
@Configuration
public class RedisConfiguration {


////    @Bean
////    JedisConnectionFactory jedisConnectionFactory() {
////        return new JedisConnectionFactory();
////    }
//
//    @Bean
//    public org.springframework.data.redis.connection.RedisConfiguration redisConnectionFactory() {
//        return  new RedisStandaloneConfiguration();
//    }

}
