package com.brettahn.webfluxstudy.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoSample2 {
    public static void main(String[] args) {
        Mono.empty() // upstream
                .subscribe(
                        data -> log.info("emitted data = {}", data),
                        error -> {},
                        () -> log.info("emitted onComplete signal")
                ); // downstream
    }
}
