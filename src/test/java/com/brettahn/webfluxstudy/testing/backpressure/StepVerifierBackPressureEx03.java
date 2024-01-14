package com.brettahn.webfluxstudy.testing.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierBackPressureEx03 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByDropStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectComplete()
                .verifyThenAssertThat()
                .hasDiscardedElements()
                .hasDiscarded(2, 3, 4, 5, 6, 98, 99, 100);
//                .hasDropped(2, 3, 4, 5, 6, 98, 99, 100); // backpressure의 drop 전략과 다름
    }
}
