package com.spring.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("{name}")
    public void hello(@PathVariable String name){

//        return "hello" + name;
    }
}
