package com.glifery.starcraftpollbot.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.glifery.starcraftpollbot.Application;
import org.springframework.boot.SpringApplication;

public class StarCraftPollRequestHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object s, Context context) {
        context.getLogger().log("Start lambda with input: " + s);
        SpringApplication.run(Application.class);
        return "End lambda successfully";
    }

}
