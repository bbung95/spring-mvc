package com.spring.null_safe;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SafeService {

    public void createEvent(@NonNull String name){
        System.out.println(name);
    }
}
