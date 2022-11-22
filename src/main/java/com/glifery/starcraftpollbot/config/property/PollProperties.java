package com.glifery.starcraftpollbot.config.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "poll")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class PollProperties {

    private final String chatId;
    private final Boolean forceSend;
    private final String times;

    public List<String> getTimes() {
        return Arrays.asList(times.split("\\|"));
    }
}
