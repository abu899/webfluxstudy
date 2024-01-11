package com.brettahn.webfluxstudy.scheduler.parallel;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel에 파라미터를 전달해 worker thread의 갯수를 지정할 수 있다
 */
public class parallelEx03 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                .parallel(2)
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
