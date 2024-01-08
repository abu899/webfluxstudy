package com.brettahn.webfluxstudy.backpressure;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * drop 전략과 비슷하지만, drop은 버퍼가 찬 경우 들어온 즉시 drop 됨
 * 하지만, latest의 경우 즉시 drop 되지 않고 다음 데이터가 들어오는 경우 최신 데이터가 아닌 데이터를 drop
 */
public class BackPressureLatestStrategy {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            TimeUtils.sleep(5L);
                            Logger.onNext(data);
                        },
                        error -> Logger.onError(error));

        TimeUtils.sleep(2000L);
    }
}
