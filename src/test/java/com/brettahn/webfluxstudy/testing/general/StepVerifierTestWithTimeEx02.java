package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class StepVerifierTestWithTimeEx02 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                                Flux.interval(Duration.ofHours(12)).take(1)
                        )
                )
                .expectSubscription()
                .thenAwait(Duration.ofHours(12)) // StepVerifier 입장에서 해당 시간을 기다린다
                .expectNextCount(11)
                .expectComplete()
                .verify();

    }
}
