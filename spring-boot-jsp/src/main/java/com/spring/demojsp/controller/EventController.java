package com.spring.demojsp.controller;

import com.spring.demojsp.entity.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @GetMapping("events")
    public String getEvents(Model model){

        List<Event> events = new ArrayList<>();
        events.add(getEventEntity("스프링 웹 MVC 스터디 1"));
        events.add(getEventEntity("스프링 웹 MVC 스터디 2"));

        model.addAttribute("events", events);
        model.addAttribute("message", "With Corona");

        return "events/list";
    }

    private Event getEventEntity(String name){

        Event event = new Event();
        event.setName(name);
        event.setStarts(LocalDateTime.now());

        return event;
    }
}
