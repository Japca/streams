package net.japca.streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StreamsReceiver {

    public static void main(String[] args) {
        SpringApplication.run(StreamsReceiver.class, args);
    }


}
