package com.glifery.starcraft_poll.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class App {
    private String chatId;
    private Boolean forceSend;
}
