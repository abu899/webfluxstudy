package com.brettahn.webfluxstudy.scheduler.operator;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn()을 사용하여 스케줄러를 지정하는 예제
 */
public class SchedulerOperatorEx04 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
