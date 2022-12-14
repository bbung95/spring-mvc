package com.springbootweb.bootwebmvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootweb.bootwebmvc.dto.OrderRequestDto;
import com.springbootweb.bootwebmvc.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class SampleApi {

    @PostMapping("events")
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @PostMapping("object-mapper")
    public OrderRequestDto asd(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderRequestDto orderRequestDto = objectMapper.readValue(jsonData, OrderRequestDto.class);

        return orderRequestDto;
    }
}
