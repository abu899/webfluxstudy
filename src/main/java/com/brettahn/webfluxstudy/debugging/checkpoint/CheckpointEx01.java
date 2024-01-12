package com.brettahn.webfluxstudy.debugging.checkpoint;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;

public class CheckpointEx01 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .checkpoint()
                .map(num -> num + 2)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
