package net.japca.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableIntegration
@RestController
public class ShopService {

	public static void main(String[] args) {
		SpringApplication.run(ShopService.class, args);
	}

//	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM, value = "/orders")
//	public Mono<ServerResponse> orders(ServerRequest request) {
//		return requestHandler.streamOrders(request);
////		Flux<String> eventFlux = Flux.fromStream(Stream.generate(() -> "Random number: " + new Random().nextInt(100)));
////		Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
////		return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);
//	}
}
