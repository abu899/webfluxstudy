package com.brettahn.webfluxstudy.sequence.hot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
public class HotSequenceExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> concertFlux =
                Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
                        .delayElements(Duration.ofSeconds(1)).share();

        concertFlux.subscribe(singer -> log.info("# Subscriber1 is watching {}'s song.", singer));
        Thread.sleep(2500);

        concertFlux.subscribe(singer -> log.info("# Subscriber2 is watching {}'s song.", singer));
        Thread.sleep(3000);
    }
}
