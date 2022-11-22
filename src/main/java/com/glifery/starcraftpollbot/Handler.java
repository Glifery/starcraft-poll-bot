package com.glifery.starcraftpollbot;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.springframework.boot.SpringApplication;

public class Handler implements RequestHandler<Object, Object> {
    @Override
    public Object handleRequest(Object s, Context context) {
        context.getLogger().log("Start lambda with input: " + s);

        SpringApplication.run(Application.class);

        return "End lambda successfully";
    }
}
