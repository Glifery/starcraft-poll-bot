package com.glifery.starcraft_poll.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class App {
    private String chatId;
    private Boolean forceSend;
    private String times;

    public List<String> getTimes() {
        return Arrays.asList(times.split("\\|"));
    }
}
