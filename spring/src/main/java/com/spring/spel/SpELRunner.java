package com.spring.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class SpELRunner implements ApplicationRunner {

    @Value("#{1 + 1}")
    private int value;

    @Value("#{'hello ' + ' world'}")
    private String greeting;

    @Value("#{1 eq 1}")
    private boolean trueOrFalse;

    @Value("${my.value}")
    private String myValue;

    @Value("#{'spring'}")
    private String spring;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("====================");
        System.out.println("value : " + value);
        System.out.println("greeting : " + greeting);
        System.out.println("trueOrFalse : " + trueOrFalse);
        System.out.println("myValue : " + myValue);
        System.out.println("spring = " + spring);
    }
}
