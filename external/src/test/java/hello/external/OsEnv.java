package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * OS 환경변수는 현재 프로그램 이외에 다른 프로그램에서도 사용 가능(전역변수)
 */
@Slf4j
public class OsEnv {
    public static void main(String[] args) {
        Map<String, String> envs = System.getenv();
        for (String s : envs.keySet()) {
            log.info(" env = {}, value = {}", s, System.getenv(s));
        }

        System.getenv("DBURL");
    }
}
