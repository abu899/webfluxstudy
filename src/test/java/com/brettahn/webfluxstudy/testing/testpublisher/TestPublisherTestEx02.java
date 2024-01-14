package com.brettahn.webfluxstudy.testing.testpublisher;

import com.brettahn.webfluxstudy.testing.general.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestEx02 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.occurError(source.flux()))
                .expectSubscription()
                .then(() -> {
                    source.next(2, 4, 6, 8);
                    source.error(new ArithmeticException()); // 개발자가 특정 상황을 재연 가능
                })
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError()
                .verify();
    }
}
