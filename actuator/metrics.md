# Metrics

## Overview

### JVM

- 메모리 및 버퍼풀 세부 정보
- 가비지 컬렉터 관련 통계
- Thread 활용
- 로드 및 언로드된 클래스 갯수
- JVM 버전 정보
- JIT 컴파일 시간

### 시스템 메트릭

`system`, `process`, `disk` 로 시작

- CPU 지표
- 파일 디스크립터 메트릭
- 가동 시간 메트릭
- 사용 가능한 디스크 공간

### 어플리케이션 시작 메트릭

- application.started.time
  - 어플리케이션을 시작하는데 걸리는 시간
  - `ApplicationStartedEvent`로 측정
  - 스프링 컨테이너가 완전히 실행된 상태
    - 이후 커맨드 라인 러너가 호출
- application.ready.time
  - 어플리케이션이 요청을 처리할 준비가 되는데 걸리는 시간
  - `ApplicationReadyEvent`로 측정
  - 커맨드 라인 러너가 실행된 이후에 호출
- 스프링은 내부에서 여러 초기화 단계가 있고 각 단계별로 내부에서 어플리케이션 이벤트를 발행

### 스프링 MVC 메트릭

스프링 MVC 컨트롤러가 처리하는 모든 요청에 대한 메트릭

- `TAG`를 사용해서 정보를 분류해서 확인 가능
  - uri
  - method
  - status
  - exception
  - outcome
    - 상태코드를 그룹으로 모아서 확인

### 데이터소스 메트릭

DataSource, 커넥션 풀과 관련된 메트릭

- `jdbc.connection.` 으로 시작
- 최대 커넥션, 최소 커넥션, 활성 커넥션, 대기 커넥션 수 확인 가능
- 히카리 커넥션풀을 사용하면 `hikaricp.`를 통해 메트릭 확인 가능

### 로그 메트릭

- `logback.events`
  - logback 로그에 대한 메트릭 확인
- trace, debug, info, warn, error 각각 로그 레벨에 따른 로그 수 확인 가능
  - error 가 급격히 높아진다면 위험하다는 식으로 인지 가능

### 톰캣 메트릭

 - `tomcat.`으로 시작
   - 모든 톰캣 메트릭을 보려면 옵션을 활성화 해야함
   - 활성화하지 않는다면 session 관련 정보만 노출
 - `tomcat.threads.busy`와 `tomcat.threads.config.max` 가 유용!
   - tomcat.threads.config.max
     - 동시에 요청받을 수 있는 요청 수
   - tomcat.threads.busy
     - 실제 동작하고 있는 쓰레드
   
### 기타

- HTTP 클라이언트 메트릭
  - RestTemplate, WebClient
- 캐시 메트릭
- 작업 실행과 스케쥴 메트릭
- Spring Data Repository 메트릭
- Mongo DB 메트릭
- Redis 메트릭

### 사용자 정의 메트릭

- 사용자가 직접 추가, 정의하는 메트릭
- 마이크로 미터의 사용법을 먼저 이해하고 사용해야함

## 프로메테우스와 그라파나

- 프로메테우스
  - 메트릭을 순간적으로 확인하는게 아닌 과거이력까지 확인하려면 메트릭을 보관하는 DB가 필요
  - 프로메테우스는 메트릭을 지속해서 수집하고 DB에 저장
- 그라파나
  - 프로메테우스로 저장한 메트릭을 사용자가 보기 편하게 보여주는 대시보드