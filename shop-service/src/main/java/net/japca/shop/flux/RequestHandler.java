package net.japca.shop.flux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * Created by Jakub krhovják on 3/31/19.
 */
    @Component
    public class RequestHandler {

        public Mono<ServerResponse> streamOrders() {
            Flux<String> eventFlux = Flux.fromStream(Stream.generate(() -> "Random number: " + new Random().nextInt(100)));
            Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
            return ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1), String.class);
        }
    }

