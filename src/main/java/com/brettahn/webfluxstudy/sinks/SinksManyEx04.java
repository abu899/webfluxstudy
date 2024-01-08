package com.brettahn.webfluxstudy.sinks;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * replay
 * limit에 지정한 개수만큼의 가장 최신 데이터를 Subscriber에게 전달한다.
 */
public class SinksManyEx04 {
    public static void main(String[] args) {
        Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        replaySink.emitNext(4, FAIL_FAST);
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
