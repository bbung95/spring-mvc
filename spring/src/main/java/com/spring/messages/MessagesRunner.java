package com.spring.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

//@Component
public class MessagesRunner implements ApplicationRunner {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String krMessage = messageSource.getMessage("greeting", new String[]{"bbung"}, Locale.getDefault());
        String engMessage = messageSource.getMessage("greeting", new String[]{"bbung"}, Locale.ENGLISH);

        System.out.println("krMessage = " + krMessage);
        System.out.println("engMessage = " + engMessage);
    }
}
