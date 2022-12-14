package com.spring.bind;

import com.spring.validation.Event;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

//    @InitBinder
//    public void init(WebDataBinder webDataBinder){
//        webDataBinder.registerCustomEditor(Event.class, new EventEditor());
//    }

    @GetMapping("event/{event}")
    public String event(@PathVariable Event event){

        System.out.println("event = " + event);

        return event.getId().toString();
    }
}
