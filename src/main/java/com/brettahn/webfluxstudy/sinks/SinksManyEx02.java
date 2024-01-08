package com.brettahn.webfluxstudy.sinks;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * multicast
 * 하나 이상의 Subscriber에게만 데이터를 emit할 수 있다.
 */
public class SinksManyEx02 {
    public static void main(String[] args) {
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, FAIL_FAST);
        multicastSink.emitNext(2, FAIL_FAST);

        // multicast 스펙에서는 첫 번째 구독이 발생하는 시점에 데이터 emit이 시작됨(hot sequence)
        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));

        multicastSink.emitNext(3, FAIL_FAST);
    }
}
