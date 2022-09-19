package com.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AoPRunner implements ApplicationRunner {

    @Autowired
    private AoPService aoPService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        aoPService.createEvent();
        aoPService.publishEvent();
    }
}
