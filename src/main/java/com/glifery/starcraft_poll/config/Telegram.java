package com.glifery.starcraft_poll.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegram")
public class Telegram {
    private String name;
    private String username;
    private String token;
}
