package com.springbootweb.bootwebmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events")
public class ExampleController {

    @GetMapping
    public String getEvents(){

        return "GET /events";
    }

    @GetMapping("{id}")
    public String getEvents(@PathVariable int id){

        return "GET /events/" + id;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postEvents(){

        return "POST /events";
    }

    @DeleteMapping("{id}")
    public String deleteEvents(@PathVariable int id){

        return "DELETE /events/" + id;
    }

    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String putEvents(@PathVariable int id){

        return "PUT /events/" + id;
    }
}
