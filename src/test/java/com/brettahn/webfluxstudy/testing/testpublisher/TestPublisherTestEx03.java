package com.brettahn.webfluxstudy.testing.testpublisher;

import com.brettahn.webfluxstudy.testing.general.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestEx03 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.takeNumber(source.flux(), 3).log())
                .expectSubscription()
                .then(() -> source.emit(1, 2, 3, 4, 5)) // emit은 내부적으로 onComplete를 호출해서 시퀀스 종료
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }
}
