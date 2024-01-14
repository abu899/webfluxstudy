package com.brettahn.webfluxstudy.testing.testpublisher;

import com.brettahn.webfluxstudy.testing.general.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestEx01 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create(); // test publisher 생성

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux())) // test publisher를 flux로 변환하여 테스트
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, 10)) // 5개의 데이터를 emit
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }
}
