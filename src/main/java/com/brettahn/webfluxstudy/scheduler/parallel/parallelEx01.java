package com.brettahn.webfluxstudy.scheduler.parallel;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;

/**
 * parallel만 있는 경우 병렬로 실행되지 않음
 */
public class parallelEx01 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel()
                .subscribe(Logger::onNext);
    }
}
