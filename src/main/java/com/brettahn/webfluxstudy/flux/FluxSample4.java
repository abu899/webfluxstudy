package com.brettahn.webfluxstudy.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxSample4 {
    public static void main(String[] args) {
        Flux.concat(
                        Flux.just("venus"),
                        Flux.just("earth"),
                        Flux.just("mars")
                ).collectList()
                .subscribe(list -> log.info("list = {}", list));
    }
}
