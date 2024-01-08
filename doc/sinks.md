# Sinks

- Reactive Streams에서 발생하는 시그널(onComplete, onError)을 프로그래밍적으로
전송할 수 있는 기능을 가진 publisher의 한 종류
- Thread-safe하지 않은 Processor와 달리 Sinks는 Thread-safe하다
- Sinks.Many 또는 Sinks.One 인터페이스를 통해 thread-safe한 시그널을 발생시킬 수 있다