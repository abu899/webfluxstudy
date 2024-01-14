package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierTestEx06 {
    @Test
    public void rangeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(GeneralExample.takeNumber(source, 500),
                        StepVerifierOptions.create().scenarioName("Verify from 0 to 499")) // 추가 옵션 지정
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498) // emit이 기대되는 갯수
                .expectNext(499)
                .expectComplete()
                .verify();
    }
}
