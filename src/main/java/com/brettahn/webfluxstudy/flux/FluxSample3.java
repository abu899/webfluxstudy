package com.brettahn.webfluxstudy.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxSample3 {
    public static void main(String[] args) {
        Flux<Object> flux =
                Mono.justOrEmpty(null)
                .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(data -> log.info("data = {}", data));
    }
}
