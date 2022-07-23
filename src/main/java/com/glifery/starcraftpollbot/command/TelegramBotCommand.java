package com.glifery.starcraftpollbot.command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public interface TelegramBotCommand<T extends Serializable> {

    BotApiMethod<T> handleUpdate(Update update);

}
