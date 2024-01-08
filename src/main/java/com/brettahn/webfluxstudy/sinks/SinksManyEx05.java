package com.brettahn.webfluxstudy.sinks;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * replay.all
 * 구독이 발생한 시점과 상관없이 모든 데이터를 replay
 */
public class SinksManyEx05 {
    public static void main(String[] args) {
        Sinks.Many<Integer> replaySink = Sinks.many().replay().all();
        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
