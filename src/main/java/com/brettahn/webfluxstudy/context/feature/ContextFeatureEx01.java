package com.brettahn.webfluxstudy.context.feature;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureEx01 {
    public static void main(String[] args) {
        final String key1 = "id";
        final String key2 = "name";

        Mono
                .deferContextual(ctx ->
                        Mono.just(ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "Kevin"))
                .transformDeferredContextual((mono, ctx) ->
                        mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Tom"))
                )
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
