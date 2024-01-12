package com.brettahn.webfluxstudy.debugging.checkpoint;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;

public class CheckpointEx02 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .map(num -> num + 2)
                .checkpoint() // 이 경우 위에 두개의 operator가 있으므로 어느 것에서 발생한지는 알 수 없음
                .subscribe(Logger::onNext, Logger::onError);
    }
}
