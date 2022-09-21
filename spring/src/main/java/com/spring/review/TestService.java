package com.spring.review;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String getData(String name){
        return "Hello, " + name;
    }
}
