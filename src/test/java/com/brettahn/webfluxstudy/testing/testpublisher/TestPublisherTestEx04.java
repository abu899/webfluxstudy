package com.brettahn.webfluxstudy.testing.testpublisher;

import com.brettahn.webfluxstudy.testing.general.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class TestPublisherTestEx04 {
    @Test
    public void divideByTwoTest() {
        // 리액티브 스트림즈 사양을 준수하지 않는지 테스트
        // emit 되기 전에 에러호출이 아닌, emit 후 데이터를 가공하는 과정에서 에러 발생
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);
//        TestPublisher<Integer> source = TestPublisher.create(); // 이렇게하면 데이터가 emit 되기 전에 에러가 호출

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, null))
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectComplete()
                .verify();
    }
}
