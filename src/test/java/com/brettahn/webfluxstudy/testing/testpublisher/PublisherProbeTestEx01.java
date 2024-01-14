package com.brettahn.webfluxstudy.testing.testpublisher;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeTestEx01 {
    @Test
    public void publisherProbeTest() {
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower());

        StepVerifier
                .create(PublisherProbeExample.processWith(PublisherProbeExample.useMainPower(), probe.mono()))
                .expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
