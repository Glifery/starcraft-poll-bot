package com.glifery.starcraft_poll;

import com.glifery.starcraft_poll.application.TelegramBot;
import com.glifery.starcraft_poll.config.App;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.function.Function;

@SpringBootApplication
@RequiredArgsConstructor
public class StarcraftPollApplication {

	private final TelegramBot telegramBot;
	private final App app;

	public static void main(String[] args) {
		SpringApplication.run(StarcraftPollApplication.class, args);
	}

	@Bean
	public Function<String, Boolean> containsCloud() {
		return value -> value.contains("cloud");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		telegramBot.doSendPoll(app.getChatId(), app.getForceSend());
	}
}
