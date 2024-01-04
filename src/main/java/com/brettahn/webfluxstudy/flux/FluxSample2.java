package com.brettahn.webfluxstudy.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxSample2 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9, 10, 30})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(multiply -> log.info("multiply = {}", multiply));
    }
}
