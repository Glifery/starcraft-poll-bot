package com.glifery.starcraft_poll;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.springframework.boot.SpringApplication;

public class StarcraftPollHandler implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object s, Context context) {
        context.getLogger().log("Start lambda with input: " + s);

        SpringApplication.run(StarcraftPollApplication.class);

        return "End lambda successfully";
    }
}
