package com.springbootweb.bootweb.controller;

import com.springbootweb.bootweb.entity.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("hello")
    public String hello(Person person){
        return "hello " + person.getName();
    }

    @GetMapping("message")
    public String message(@RequestBody String body){
        return body;
    }

    @GetMapping("json-message")
    public Person jsonMessage(@RequestBody Person person){
        return person;
    }
}
