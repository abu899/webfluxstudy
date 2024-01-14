package com.brettahn.webfluxstudy.testing.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierBackPressureEx02 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat() // 위에 로직이 수행된 이후에 추가적인 검증 수행
                .hasDiscardedElements() // discard 된 데이터가 있는지 검증
                .hasDiscarded(2) // discard 된 데이터 중 2가 있는지 검증
                .hasDroppedElements() // drop 된 데이터가 있는지 검증
                .hasDropped(3, 4, 5, 6, 98, 99, 100); // drop 된 데이터 중 3, 4, 5, 6, 98, 99, 100이 있는지 검증
    }
}
