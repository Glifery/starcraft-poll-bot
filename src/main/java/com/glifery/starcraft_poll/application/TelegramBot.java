package com.glifery.starcraft_poll.application;

import com.glifery.starcraft_poll.config.Telegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final Telegram telegram;
    private final PollOptionsProvider pollOptionsProvider;

    @Override
    public String getBotUsername() {
        return telegram.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegram.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Boolean isStartMessage = Optional.ofNullable(update.getMessage().getText())
                .map(text -> text.equals("/start"))
                .orElse(false);

        if (isStartMessage.equals(false)) {
            return;
        }

        String chatId = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(String.format("Chat id: %s", chatId));

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void doSendPoll(String chatId, Boolean forceSend) {
        pollOptionsProvider.getOptions(new Date(), forceSend)
                .ifPresent(options -> {
                    try {
                        execute(createPoll(chatId, options));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                });
    }

    private SendPoll createPoll(String chatId, List<String> options) {
        return SendPoll.builder()
                .chatId(chatId)
                .question("game")
                .options(options)
                .isAnonymous(false)
                .allowMultipleAnswers(false)
                .build();
    }
}
