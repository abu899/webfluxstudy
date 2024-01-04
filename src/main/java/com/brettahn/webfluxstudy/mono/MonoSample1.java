package com.brettahn.webfluxstudy.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoSample1 {
    public static void main(String[] args) {
        Mono.just("Hello Mono") // upstream
                .subscribe(
                        data -> log.info("emitted data = {}", data) // 데이터를 전달받아 처리
                ); // downstream
    }
}
