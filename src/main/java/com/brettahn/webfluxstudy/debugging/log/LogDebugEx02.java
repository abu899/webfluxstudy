package com.brettahn.webfluxstudy.debugging.log;

import com.brettahn.webfluxstudy.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.HashMap;
import java.util.Map;

public class LogDebugEx02 {
    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Hooks.onOperatorDebug();

        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .log()
                .map(String::toLowerCase)
                .log()
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log()
                .map(fruits::get)
                .log()
                .subscribe(Logger::onNext, Logger::onError);
    }
}
