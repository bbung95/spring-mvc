package com.springbootweb.bootwebmvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootweb.bootwebmvc.entity.Event;
import com.springbootweb.bootwebmvc.view.EventJsonView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class SampleApi {

    @PostMapping("events")
    @JsonView(EventJsonView.EventView.class)
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }
}
