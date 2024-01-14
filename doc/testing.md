# Testing

## StepVerifier

### 일반적인 테스트 

- Flux, Mono로 선언된 operator chain를 구독했을 때, 해당 동작 방식을 테스트하기 위한 가장 일반적인 방식
- expectXXX()를 통해 시퀀스 상에서 예상되는 signal의 기대 값을 assert 할 수 있음
- verify()를 통해 테스트를 트리거
  - verifyXXX()를 이용해 operator chain의 테스트를 트리거하고 종료, 에러 이벤트를 검증
- 실제 수행되는 시간과 관련된 테스트 수행 가능

### Backpressure 테스트

- hasDropped(), hasDiscarded() 이용

### Context 테스트

- expectAccessibleContext()를 이용해 접근 가능한 Context가 있는지 확인
- hasKey()를 이용해 특정 key가 있는지 확인

### emit된 데이터를 기록하여 해당 기록을 이용한 테스트

- recordWith()를 이용해 emit된 데이터를 기록
- consumeRecordedWith()를 이용해 기록된 데이터를 소비하여 검증
- expectRecordedMatches()를 이용해 기록된 데이터의 컬렉션을 검증

## TestPublisher

- Testing 목적 publisher
  - 개발자가 직접 signal을 발생시킬 수 있음
- 특정 상황을 재현하여 테스트하고 싶은 경우 사용
- 리액티브 스트림즈 사양을 준수하는지 여부를 테스트 가능
  - 널값을 emit 할 수 없고
  - subscriber가 구독을 취소했는데 계속해서 onNext()를 호출하면 안됨

## PublisherProbe

- Operator chain의 실행 경로를 검증
  - 조건에 따른 분기로 인해 시퀀스가 분기되는 경우, 실행 경로를 추적하여 정상 실행을 확인
- 실행 경로대로 실행되었는지 여부를 assertXXX()를 통해 검증

