# Debug

## Debug 모드 사용

- reactive programming은 비동기로 동작하므로 stacktrace 만으로는 문제의 발생위치를 확인하기 어려움
- debug 모드 시, operator의 stacktrace를 캡쳐한 뒤 디버깅에 필요한 정보를 측정
- Hooks.onOperatorDebug()를 통해 디버깅을 활성화
  - operator chain이 선언되기 전에 선언해야 함
- 디버그 모드를 활성화하면, operator chain에서 에러 발생 시 에러가 발생한 operator 위치를 알려줌
  - traceback
- 사용이 쉽지만, 어플리케이션 내 모든 operator의 assembly를 캡쳐하기에 비용이 많이 듬

### 디버깅 용어

- StackTrace
  - 호출된 메서드에 대한 stack frame에 대한 리포트
- assembly
  - 새로운 flux가 선언된 지점
  - operator가 하나 추가될 때마다 새로운 flux가 생성되므로 이를 assembly라 함
- traceback
  - 실패한 operator의 stacktrace 정보

## checkpoint()를 이용한 Debugging

- 특정 operator chain 내에서만 assembly stacktrace를 캡쳐
- checkpoint(description)을 사용하면, 해당 checkpoint를 추가한 지점의 stacktrace 대신 description을 통해 확인 가능
  - 만약 assembly stacktrace을 확인하고 싶다면, checkpoint("description", true)를 사용

## log()를 이용한 Debugging

- checkpoint()와 유사하게 동작
- Flux, Mono 에서 발생하는 signal event를 출력
  - onNext, onError, onComplete, subscriptions, requests, cancel
- 여러 개를 사용 가능하며, operator마다 전파되는 signal을 확인 가능
- Custom Category 파라미터를 이용해 operator마다 출력되는 signal을 구분 가능
- 에러 발생 시, stacktrace도 출력
- debug 모드 활성 상태라면 traceback도 출력