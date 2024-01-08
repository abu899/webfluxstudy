package com.brettahn.webfluxstudy.backpressure;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * 버퍼가 가득차면 데이터가 drop 되었다가 버퍼가 비워졌을 때, 다시 drop 되지 않은 데이터부터 subscriber 쪽에 전달
 */
public class BackPressureDropStrategy {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureDrop(dropped -> Logger.info("# dropped: {}", dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            TimeUtils.sleep(5L);
                            Logger.onNext(data);
                        },
                        error -> Logger.onError(error));

        TimeUtils.sleep(2000L);
    }
}
