package com.brettahn.webfluxstudy.backpressure;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * 버퍼가 가득 찼을 때 Exception을 발생
 */
public class BackPressureErrorStrategy {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureError() // Error 전략 적용
                .doOnNext(Logger::doOnNext)
                .publishOn(Schedulers.parallel()) // 스레드를 하나 더 추가
                .subscribe(data -> {
                            TimeUtils.sleep(5L);
                            Logger.onNext(data);
                        },
                        error -> Logger.onError(error));

        TimeUtils.sleep(2000L);
    }
}
