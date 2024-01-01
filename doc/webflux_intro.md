# Spring Webflux

## Spring MVC vs Spring Webflux

<p align="center"><img src="./img/2.png" width="80%"></p>

### Spring MVC

- Blocking I/O 기반
- 요청당 하나의 스레드
- 과도한 Thread 사용으로 인해 CPU 대기 시간이 늘어나고 메모리 사용 오버헤드 발생

### Webflux

- Non-Blocking I/O 기반
- 하나의 스레드로 대량의 요청 처리 가능
- 적은 수의 스레드로 인해 CPU와 메모리를 효율적으로 사용 가능
- CPU를 많이 사용하는 복잡한 요청을 하는 경우 다른 요청을 처리할 수 없음
- 요청에서 응답까지 모두 Non-Blocking 이여야 함

## Webflux Intro

- Spring 5.0에서 새롭게 추가된 리액티브 웹 프레임워크
- 비동기 Non-Blocking I/O를 지원하는 리액티브 스트림즈를 기반으로 함
  - 리액티브 스트림즈의 구현체 중 하나인 Reactor를 사용
  - Reactor 기반이지만, 다른 리액티브 확장 라이브러리를 적용 가능

### Spring Webflux를 사용하기 적합한 시스템 

- Blocking I/O 방식으로 처리하는데 한계가 있는 대량의 트래픽이 발생하는 시스템
- 마이크로 서비스 기반 시스템
  - 다수의 I/O 발생
- 스트리밍 시스템 및 실시간 시스템
- 네트워크 접속이 느린 클라이언트 요청 처리