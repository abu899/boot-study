package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandLIneBean {

    private final ApplicationArguments arguments;

    @PostConstruct
    public void init() {
        log.info("source = {} ", List.of(arguments.getSourceArgs()));
        log.info("getOptionNames = {} ", List.of(arguments.getOptionNames()));
        Set<String> optionNames = arguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option arg = {}, value ={}", optionName, arguments.getOptionValues(optionName));
        }
    }
}
