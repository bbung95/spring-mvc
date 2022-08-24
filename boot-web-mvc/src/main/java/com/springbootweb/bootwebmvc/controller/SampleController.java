package com.springbootweb.bootwebmvc.controller;

import com.springbootweb.bootwebmvc.entity.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("valid")
    public String valid(Model model){

        Event event = new Event();
        event.setLimit(0);
        model.addAttribute("event", event);

        return "valid/form";
    }

    @PostMapping("valid")
    public String valid(@Validated @ModelAttribute Event event, BindingResult bindingResult){
        
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(item -> System.out.println("item = " + item));
            return "valid/form";
        }

        return "redirect:/valid/list";
    }

    @GetMapping("valid/list")
    public String validList(Model model){

        Event event = new Event();
        event.setName("bbung");
        event.setLimit(10);

        List<Event> list = new ArrayList<>();
        list.add(event);
        model.addAttribute("list", list);

        return "valid/list";
    }

}
