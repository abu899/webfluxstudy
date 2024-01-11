package com.brettahn.webfluxstudy.context.feature;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ContextFeatureEx03 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itWorld")) // 마지막의 값으로 덮어 씌움
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
