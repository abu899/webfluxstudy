package com.brettahn.webfluxstudy.scheduler.kind;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class NewBoundedElasticEx01 {
    public static void main(String[] args) {
        /**
         * 쓰레드와 큐의 갯수를 지정 가능
         * 쓰레드와 큐가 모두 가득찬 이후에 새로운 subscribe가 발생하면 Exception이 발생
         */
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono =
                Mono
                        .just(1)
                        .subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 3 doing", data);
            TimeUtils.sleep(3000L);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 4 doing", data);
            TimeUtils.sleep(3000L);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 5 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 6 doing", data);
        });

//        mono.subscribe(data -> {
//            Logger.onNext("subscribe 6 doing", data);
//        });
//
//        mono.subscribe(data -> {
//            Logger.onNext("subscribe 6 doing", data);
//        });

//        TimeUtils.sleep(4000L);
//        scheduler.dispose();
    }
}
