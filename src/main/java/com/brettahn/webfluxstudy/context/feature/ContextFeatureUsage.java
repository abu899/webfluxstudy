package com.brettahn.webfluxstudy.context.feature;

import com.brettahn.webfluxstudy.util.Logger;
import com.brettahn.webfluxstudy.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * 직교성을 가지는 정보를 표현할 때 Context를 주로 사용한다
 * 즉, 어플리케이션 실행에 영향을 주지않는 정보, 대표적으로 token 값
 */
public class ContextFeatureUsage {
    public static final String HEADER_NAME_AUTH_TOKEN = "authToken";
    public static void main(String[] args) {
        Mono<String> mono =
                postBook(Mono.just(
                        new Book("abcd-1111-3533-2809"
                                , "Reactor's Bible"
                                ,"Kevin"))
                )
                        .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));

        mono.subscribe(Logger::onNext);

    }

    private static Mono<String> postBook(Mono<Book> book) {
        return Mono.zip(book, Mono.deferContextual(ctx -> Mono.just(ctx.get(HEADER_NAME_AUTH_TOKEN))))
                .flatMap(tuple -> Mono.just(tuple))  // 외부 API 서버로 HTTP POST request를 전송한다고 가정
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName() +
                            "," + tuple.getT1().getAuthor() + ") with token: " +
                            tuple.getT2();
                    return Mono.just(response); // HTTP response를 수신했다고 가정
                });
    }
}
