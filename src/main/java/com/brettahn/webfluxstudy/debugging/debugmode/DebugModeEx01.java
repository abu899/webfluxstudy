package com.brettahn.webfluxstudy.debugging.debugmode;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class DebugModeEx01 {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();

        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
