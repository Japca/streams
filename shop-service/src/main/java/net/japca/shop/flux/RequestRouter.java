package net.japca.shop.flux;

import net.japca.common.Order;
import net.japca.shop.redis.RedisConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Configuration
public class RequestRouter {

    @Autowired
    private RedisConsumer redisConsumer;

    @Bean
    RouterFunction<?> routes() {
        Flux<Order> eventFlux = Flux.fromStream(Stream.generate(() -> new Order("desc", 1)));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        Mono<ServerResponse> body = ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1), Order.class);
        return RouterFunctions.route(RequestPredicates.GET("/orders"), request -> body);
    }
}
