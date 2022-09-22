package com.spring.rest.events.controller;

import com.spring.rest.events.domain.Event;
import com.spring.rest.events.dto.EventDto;
import com.spring.rest.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    @PostMapping
    public ResponseEntity createEvents(@RequestBody EventDto event){

//        Event save = eventRepository.save(event);

        URI uri = linkTo(methodOn(EventController.class).createEvents(event)).slash("{id}").toUri();

        return ResponseEntity.created(uri).body(event);
    }
}
