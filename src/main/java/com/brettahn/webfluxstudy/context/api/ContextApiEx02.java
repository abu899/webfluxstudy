package com.brettahn.webfluxstudy.context.api;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class ContextApiEx02 {
    public static void main(String[] args) {
        String key1 = "id";
        String key2 = "name";
        String key3 = "country";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1) + ", " + "Name: " + ctx.get(key2) +
                                ", " + "Country: " + ctx.get(key3))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.putAll(Context.of(key2, "Kevin", key3, "Korea").readOnly()))
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
