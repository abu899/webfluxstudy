package com.brettahn.webfluxstudy.scheduler.operator;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;

/**
 * subscribe가 실행된 scope의 스레드와 동일한 스레드에서 실행된다
 */
public class SchedulerOperatorEx01 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .filter(data -> data > 3)
                .map(data -> data * 10)
                .subscribe(Logger::onNext);
    }
}
