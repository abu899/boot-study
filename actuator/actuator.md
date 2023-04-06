# Actuator

## Endpoint list

- beans
  - 스프링 컨테이너에 등록된 스프링 빈
- condition
  - `condition`을 통해서 빈을 등록할 때 평가 조건과 일치하거나 일치하지 않는 이유 표시
- configprops
  - `@ConfigurationProperties` 를 보여줌
- env
  - `Environment` 정보
- health
  - 어플리케이션 헬스 정보(서버가 올라가있나 내려가있나)
  - 어플리케이션에 문제가 생겼을 때 빠르게 인지 가능
  - 단순 요청 응답이 가능한지 판단이 아닌, 어플리케이션이 사용하는 DB 가 응답하는지, 디스크 사용량에 문제가 없는지에 대한
    정보를 포함해서 만들어짐
- httpexchanges
  - HTTP 호출 응답 정보를 보여줌
  - `HttpExchangeRepository`를 구현한 빈을 별도 등록해야함
- info
  - 어플리케이션 정보
  - 기본 제공 정보
    - java 런타임 정보
      - 기본 비활성화
    - os 정보
      - 기본 비활성화
    - Environment 에서 `info.`으로 시작하는 정보
      - 기본 비활성화
    - build 정보
      - `META-INF/build-info.properties`파일이 필요
    - git 정보
      - 어느 깃 브랜치에서 어느 커밋으로 빌드 됬는지
      - `git.properties` 파일이 필요
- loggers
  - 어플리케이션 로거 설정을 보여주고 변경 또한 가능
- metrics
  - 어플리케이션 메트릭 정보
- mapping
  - `@RequestMapping` 정보
- threaddump
  - 쓰레드 덤프를 실행해서 보여줌
- shutdown
  - 기본 비활성화
  - 어플리케이션 종료 