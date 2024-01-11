package com.brettahn.webfluxstudy.context.feature;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureEx04 {
    public static void main(String[] args) {
        String key1 = "id";
        Mono.just("Kevin")
                .transformDeferredContextual((stringMono, contextView) -> contextView.get("job")) // 외부에서는 내부에서 정의된 context를 알 수 없다.
                .flatMap(name -> Mono.deferContextual(ctx ->
                                Mono.just(ctx.get(key1) + ", " + name) // 내부에서는 외부에 정의된 context를 알 수 있다.
                                        .transformDeferredContextual((mono, innerCtx) ->
                                                mono.map(data -> data + ", " + innerCtx.get("job"))
                                        )
                                        .contextWrite(context -> context.put("job", "Software Engineer")) // inner sequence context
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
