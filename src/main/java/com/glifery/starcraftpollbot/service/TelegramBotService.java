package com.glifery.starcraftpollbot.service;

import com.glifery.starcraftpollbot.api.TelegramBot;
import com.glifery.starcraftpollbot.config.property.PollProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TelegramBotService {

    private static final String POLL_QUESTION = "game";

    private final PollProperties pollProperties;
    private final TelegramBot telegramBot;

    public void sendPoll(Collection<String> options) {
        String chatId = pollProperties.getChatId();
        SendPoll sendPoll = SendPoll.builder()
                .chatId(chatId)
                .question(POLL_QUESTION)
                .options(options)
                .isAnonymous(Boolean.FALSE)
                .allowMultipleAnswers(Boolean.FALSE)
                .build();
        telegramBot.execute(sendPoll);
    }

}
