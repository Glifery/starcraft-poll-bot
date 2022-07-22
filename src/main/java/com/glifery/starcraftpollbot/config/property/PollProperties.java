package com.glifery.starcraftpollbot.config.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "poll")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class PollProperties {

    private final String chatId;
    private final Boolean forceSend;

}