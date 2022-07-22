package com.glifery.starcraftpollbot.runner;

import com.glifery.starcraftpollbot.service.PollService;
import com.glifery.starcraftpollbot.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramBotRunner implements ApplicationRunner {

    private static final String MINSK_TIME_OFFSET = "+03:00";
    private final TelegramBotService telegramBotService;
    private final PollService pollService;

    @Override
    public void run(ApplicationArguments args) {
        ZoneId minskZoneId = ZoneId.of(MINSK_TIME_OFFSET);
        LocalDate now = LocalDate.now(minskZoneId);
        List<String> options = pollService.getOptions(now);
        if (!options.isEmpty()) {
            telegramBotService.sendPoll(options);
        }
    }

}
