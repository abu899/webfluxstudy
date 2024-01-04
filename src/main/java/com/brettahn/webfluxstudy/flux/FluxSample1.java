package com.brettahn.webfluxstudy.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxSample1 {
    public static void main(String[] args) {
        Flux.just(6, 9 , 10)
                .map(num -> num % 2)
                .subscribe(remainder -> log.info("remainder = {}", remainder));
    }
}
