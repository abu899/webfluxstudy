package com.brettahn.webfluxstudy.testing.record;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.Every.everyItem;

public class StepVerifierRecordTestEx01 {
    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
                .expectSubscription()
                .recordWith(ArrayList::new) // emit된 데이터를 기록
                .thenConsumeWhile(country -> !country.isEmpty()) // 조건에 맞는 데이터를 소비, 이 조건에 맞아야 arrayList에 기록
                .consumeRecordedWith(countries -> { // 기록된 데이터를 소비
                    assertThat(countries, everyItem(hasLength(6)));
                    assertThat(
                            countries
                                    .stream()
                                    .allMatch(country -> Character.isUpperCase(country.charAt(0))),
                            is(true)
                    );
                })
                .expectComplete()
                .verify();
    }
}
