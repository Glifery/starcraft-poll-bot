package com.glifery.starcraft_poll;

import com.glifery.starcraft_poll.application.TelegramBot;
import com.glifery.starcraft_poll.config.App;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootConfiguration
//@RequiredArgsConstructor
public class StarcraftPollApplication implements ApplicationContextInitializer<GenericApplicationContext> {

//	private final TelegramBot telegramBot;
//	private final App app;

	public static void main(String[] args) {
		FunctionalSpringApplication.run(StarcraftPollApplication.class, args);
	}

	public Function<String, String> containsCloud() {
		return value -> "Hello world";
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void start() {
//		telegramBot.doSendPoll(app.getChatId(), app.getForceSend());
//	}

	@Override
	public void initialize(GenericApplicationContext applicationContext) {
		applicationContext.registerBean("containsCloud", FunctionRegistration.class,
				() -> new FunctionRegistration<>(containsCloud())
						.type(FunctionType.from(String.class).to(Boolean.class)));
	}
}
