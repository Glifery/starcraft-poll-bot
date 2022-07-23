package com.glifery.starcraftpollbot.config.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "telegram")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class TelegramBotProperties {

    private final String name;
    private final String username;
    private final String token;

}
