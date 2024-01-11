# Context

- Reactor Sequence 상에서 상태를 저장하고, operator chain에서 저장된 값을 공유해 사용할 수 있는 인터페이스
- Context는 key-value 쌍으로 구성되어 있음
- 값을 저장할 때는 contextWrite(), 값을 읽을 때는 contextView를 사용
- ContextView는 Reactor Sequence에서 deferContextual(), transformDeferredContextual()을 통해 제공
  - datasource level -> deferContextual()
  - operator level -> transformDeferredContextual()

## 자주 사용되는 Context API

### Context

- put(key, value)
  - key, value 형태로 context에 값을 저장
- Context.of(key1, value1, key2, value2, ...)
  - 여러개의 key-value 쌍을 한번에 저장
- putAll(ContextView)
  - 파라미터로 입력된 ContextView를 merge
- delete(key)
  - 파라미터로 입력된 key에 해당하는 값을 삭제

### ContextView

- get(key)
  - ContextView에서 key에 해당하는 값을 반환
- getOrEmpty(key)
  - ContextView에서 key에 해당하는 값을 Optional 형태로 반환
- getOrDefault(key, defaultValue)
  - ContextView에서 key에 해당하는 값을 반환, 값이 없을 경우 defaultValue를 반환
- hasKey(key)
  - ContextView에서 key에 해당하는 값이 있는지 확인
- isEmpty()
  - ContextView가 비어있는지 확인
- size()
  - ContextView의 크기를 반환

## Context의 특징

- Context는 각각의 Subscriber를 통해 Reactor Sequence에 연결되며, 각 operator의 실행 쓰레드가 달라도 연결된 context에 접근할 수 있음
  - Subscriber 하나당 Context 하나가 연결
- Conext는 operator chain의 아래서부터 위로 전파된다
  - Downstream에서 Upstream으로 전파
  - context read가 write 밑에 있는 경우 read가 되지 않음
  - 일반적으로 write는 operator chain의 가장 마지막에 둠
- 동일한 키에 대해서는 값을 덮어 씌움
- operator 내부에서 flatmap() 등 inner sequence에서 정의된 context는 외부 operator에서 볼 수 없음
