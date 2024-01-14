package com.brettahn.webfluxstudy.testing.record;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

public class StepVerifierRecordTestEx02 {
    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .expectRecordedMatches(countries -> // true, false 를 검증
                        countries
                                .stream()
                                .allMatch(country ->
                                        Character.isUpperCase(country.charAt(0))))
                .expectComplete()
                .verify();
    }
}
