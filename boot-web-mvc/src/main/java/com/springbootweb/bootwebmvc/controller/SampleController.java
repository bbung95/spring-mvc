package com.springbootweb.bootwebmvc.controller;

import com.springbootweb.bootwebmvc.entity.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
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

    @GetMapping("valid/form/name")
    public String validFormName(Model model){

        model.addAttribute("event", new Event());
        // @SessionAttributes("event")의 명시된 name으로 Model에 넣을시 Session Scope에 저장한다.

        return "valid/form-name";
    }

    @PostMapping("valid/form/name")
    public String validNameSubmit(@Validated @ModelAttribute Event event,
                        BindingResult bindingResult){
        
        if(bindingResult.hasErrors()){
            return "valid/form-name";
        }
        return "redirect:/valid/form/limit";
    }

    @GetMapping("valid/form/limit")
    public String validFormLimit(@ModelAttribute Event event, Model model){
        model.addAttribute("event", event);

        return "valid/form-limit";
    }

    @PostMapping("valid/form/limit")
    public String validLimitSubmit(@Validated @ModelAttribute Event event,
                             BindingResult bindingResult,
                             SessionStatus status){

        if(bindingResult.hasErrors()){
            return "valid/form-limit";
        }
        status.setComplete();

        return "redirect:/valid/list";
    }

    @GetMapping("valid/list")
    public String validList(Model model, @SessionAttribute LocalDateTime visitTime){

        System.out.println("visitTime = " + visitTime);
        
        Event event = new Event();
        event.setName("bbung");
        event.setLimit(10);

        List<Event> list = new ArrayList<>();
        list.add(event);
        model.addAttribute("list", list);

        return "valid/list";
    }
}
