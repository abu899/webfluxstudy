package com.brettahn.webfluxstudy.testing.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StepVerifierBackPressureEx01 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L) // 1개의 데이터만 요청
                .thenConsumeWhile(num -> num >= 1) // emit 된 데이터들을 소비한다.
                .verifyComplete();
    }
}
