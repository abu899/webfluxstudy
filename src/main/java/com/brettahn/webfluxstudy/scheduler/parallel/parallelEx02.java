package com.brettahn.webfluxstudy.scheduler.parallel;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel을 통해 반환된 ParallelFlux를 runOn을 통해 Scheduler를 지정해주어야 병렬 작업 실행
 * CPU의 코어 갯수 내에서 worker thread가 할당된다
 */
public class parallelEx02 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
