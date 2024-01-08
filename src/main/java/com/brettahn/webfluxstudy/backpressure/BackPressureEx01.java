package com.brettahn.webfluxstudy.backpressure;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureEx01 {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(Logger::doOnNext)  // Upstream에서 emit한 데이터를 출력
                .doOnRequest(Logger::doOnRequest) // Subscriber에서 요청한 데이터의 갯수를 출력
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        TimeUtils.sleep(2000L);
                        Logger.onNext(value);
                        request(1);
                    }
                });
    }
}
