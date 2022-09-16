package com.spring.validation;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

//@Component
public class ValidatorRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Event event = new Event();

//        EventValidator eventValidator = new EventValidator();

        Errors error = new BeanPropertyBindingResult(event, "event");

//        eventValidator.validate(event, error);

        System.out.println("error.hasErrors() = " + error.hasErrors());

        error.getAllErrors().forEach(e -> {
            System.out.println("===== error code =====");
            System.out.println("e = " + e.getCode());
            System.out.println(e.getDefaultMessage());
        });
    }
}
