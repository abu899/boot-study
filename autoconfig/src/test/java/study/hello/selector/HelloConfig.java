package study.hello.selector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

    @Bean
    public HelloBeanTest helloBeanTest() {
        return new HelloBeanTest();
    }
}
