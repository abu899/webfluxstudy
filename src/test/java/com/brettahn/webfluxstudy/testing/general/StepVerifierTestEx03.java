package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierTestEx03 {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi") // 실패 시 출력되는 메시지
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete(); // 테스트 트리거와 함께 onComplete 검증
    }
}
