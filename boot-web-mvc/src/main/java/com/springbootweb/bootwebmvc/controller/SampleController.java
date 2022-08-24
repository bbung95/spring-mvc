package com.springbootweb.bootwebmvc.controller;

import com.springbootweb.bootwebmvc.entity.Event;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetHelloMapping
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("matrix/{pathId}")
    @ResponseBody
    public Event matrix(@PathVariable int pathId, @MatrixVariable(pathVar = "pathId") MultiValueMap <String, String> map){

        Event event = new Event();
        event.setId(pathId);
        event.setName(map.toString());

        return event;
    }

}
