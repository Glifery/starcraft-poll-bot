package com.glifery.starcraftpollbot.api;

import com.glifery.starcraftpollbot.config.property.TelegramBotProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    public static final String START_COMMAND = "/start";

    private final TelegramBotProperties telegramBotProperties;

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        boolean startCommand
                = Optional.ofNullable(update.getMessage())
                .map(Message::getText)
                .filter(START_COMMAND::equals)
                .isPresent();
        if (!startCommand) {
            return;
        }

        String chatId = update.getMessage().getChatId().toString();
        String text = "Chat id: " + chatId;
        SendMessage message = new SendMessage(chatId, text);
        this.execute(message);
    }

    @Override
    public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) {
        try {
            return super.execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
