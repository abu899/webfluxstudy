package com.brettahn.webfluxstudy.testing.general;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierTestEx02 {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .expectNext("Hello")
                .expectNext("Reactor")
                .expectComplete()
                .verify();
    }
}
