# Prometheus

메트릭을 수집하고 보관하는 작업을 위해서는 두가지 작업이 필요

1. 어플리케이션 설정
    - 프로메테우스에서 어플리케이션 메트릭을 가져가야 DB에 저장할 수 있음
    - 따라서 어플리케이션에서 프로메테우스 포맷에 맞추어 메트릭 만들기
- `implementation 'io.micrometer:micrometer-registry-prometheus'` 추가
    - 포맷의 차이가 존재하기에 이를 변환해줌
        - `COUNT`처럼 지속적으로 증가하는 `카운터 메트릭`은 `_total`이 붙음
        - CPU 사용량처럼 늘었다 줄었다 하는 것을 `게이지(gauge) 메트릭`이라고 함
        - http.server.requests
            - 프로메테우스에서는 태그별로 sum, seconds, max 로 자세하게 나눠짐

2. 프로메테우스 설정
    - 프로메테우스에서 어플리케이션의 메트릭을 주기적으로 수집하도록 설정
- 프로메테우스 폴더 내의 `prometheus.yml`을 수정
```yaml
# 프로메테우스 설정
- job_name: "spring-actuator" # 원하는 이름
  metrics_path: '/actuator/prometheus'
  scrape_interval: 1s # 수집 주기 (보통 10s - 1min)
  static_configs:
    - targets: ['localhost:8080'] # 타겟 서버의 주소
```

## 기본 기능

- 태그, 레이블
    - error, exception, instance, job, method, outcome, status, uri
    - 각각은 메트릭 정보를 구분해 사용하기 위한 태그
    - 프로메테우스에서는 `레이블`, 마이크로미터에서는 `태그`
    - 라인의 끝을 보면 숫자가 있는데 각 메트릭의 값
- 필터
    - 레이블을 기준으로 필터를 사용 가능
    - 중괄호 {} 문법을 사용
    - 일치 연산자
        - =, !=
        - =~, !~
            - 정규식 사용가능
    - `+,-,*,/,&,^`와 같은 연산자 지원
    - sum, count, 지원
        - sum by()
            - sql group by와 비슷
    - topk(n, metric)
        - 상위 몇개를 추려서 보여줌
    - offset
        - 현재를 기준으로 과거 몇 분만을 보여줌
    - 벡터 범위 선택기
        - metric[시간]
        - square bracket 내의 시간의 데이터를 풀어서 보여줌
        - 그래프에 바로 표현은 불가하고 상대적인 증가 확인법을 통해 확인 가능

## 게이지와 카운터

- 게이지
    - 임의로 오르고 내리는 값
    - CPU 사용량, 메모리 사용량, 사용중인 커넥션
    - 현재의 값을 보면 되는 값이기에 단순하고 사용하기 쉬움
- 카운터
    - 단순하게 증가하는 단일 누적 값
    - HTTP 요청 수, 로그 발생 수
    - 하지만 일반적으로 단순한 누적 값이 아닌 특정 시간대에 얼마나 요청이 되었는지가 중요
        - 이런 것을 확인하기 위해 `increase`와 `rate`같은 함수를 지원
            - `increase`는 숫자를 직접 카운트
            - `rate`는 벡터 범위로 설정된 시간을 기준으로 비율을 보여줌
        - 이때 `벡터 범위 선택기`를 사용하게 됨!
        - irate
            - 범위 벡터에서 초당 순간 증가율 계산
            - 즉, 급격하게 증가한 내용을 확인하는 용도