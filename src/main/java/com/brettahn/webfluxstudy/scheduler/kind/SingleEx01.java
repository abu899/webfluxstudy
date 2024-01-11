package com.brettahn.webfluxstudy.scheduler.kind;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SingleEx01 {
    public static void main(String[] args) {
        doTask("task1")
                .subscribe(Logger::onNext);

        doTask("task2")
                .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.single())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext(taskName, "filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext(taskName, "map", data));
    }
}
