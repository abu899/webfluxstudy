package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * 특정 시간안에 테스트가 끝나나 테스트
 */
public class StepVerifierTestWithTimeEx03 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .create(TimeBasedExample.getCOVID19Count(
                                Flux.interval(Duration.ofMinutes(1)).take(1)
                        )
                )
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }
}
