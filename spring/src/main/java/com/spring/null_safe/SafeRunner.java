package com.spring.null_safe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class SafeRunner implements ApplicationRunner {

    @Autowired
    private SafeService safeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        safeService.createEvent(null);
    }
}
