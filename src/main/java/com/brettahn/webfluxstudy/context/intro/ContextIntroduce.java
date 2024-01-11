package com.brettahn.webfluxstudy.context.intro;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * publishOn, subScribeOn에 의해 서로 다른 쓰레드에서 실행되지만, Context를 이용해 내부에 저장된 상태 값을 공유해 사용할 수 있음
 */
public class ContextIntroduce {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> mono = Mono.deferContextual(ctx ->
                        Mono.just("Hello" + " " + ctx.get(key)).doOnNext(Logger::doOnNext)
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono2, ctx) -> mono2.map(data -> data + " " + ctx.get(key)))
                .contextWrite(context -> context.put(key, "Reactor"));


        mono.subscribe(data -> Logger.onNext(data));

        TimeUtils.sleep(100L);
    }
}
