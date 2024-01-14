package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

/**
 * 12시간 이후에 데이터 emit이 제대로 되는지 테스트
 */
public class StepVerifierTestWithTimeEx01 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                                Flux.interval(Duration.ofHours(12)).take(1) // interval: 특정 시점까지 emit 지연
                        )
                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler.get()
                        .advanceTimeBy(Duration.ofHours(12))) // StepVerifier 입장에서 해당 시간을 건너뛴다
                .expectNextCount(11)
                .expectComplete()
                .verify();

    }
}
