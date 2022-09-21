package com.spring.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("{name}")
    public String hello(@PathVariable String name){

        System.out.println(testService.getData(name));

        return "hello";
    }

    @GetMapping
    public String index(){

        return "index";
    }
}
