package com.springbootweb.bootwebmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("example")
public class ExampleController {

    @GetMapping
    public String getExample(){

        return "GET /example";
    }

    @GetMapping("{id}")
    public String getExample(@PathVariable int id){

        return "GET /example/" + id;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postExample(){

        return "POST /example";
    }

    @DeleteMapping("{id}")
    public String deleteExample(@PathVariable int id){

        return "DELETE /example/" + id;
    }

    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String putExample(@PathVariable int id){

        return "PUT /example/" + id;
    }
}
