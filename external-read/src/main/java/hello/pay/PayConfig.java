package hello.pay;

import hello.pay.LocalPayClient;
import hello.pay.ProdPayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class PayConfig {

    @Bean
    @Profile({"default", "dev"})
    public LocalPayClient localPayClient() {
        log.info("Local Pay Client 등록");
        return new LocalPayClient();
    }

    @Bean
    @Profile("prod")
    public ProdPayClient prodPayClient() {
        log.info("Production Pay Client 등록");
        return new ProdPayClient();
    }
}
