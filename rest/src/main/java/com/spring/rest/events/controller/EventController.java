package com.spring.rest.events.controller;

import com.spring.rest.events.domain.Event;
import com.spring.rest.events.dto.EventDto;
import com.spring.rest.events.repository.EventRepository;
import com.spring.rest.events.valid.EventValidator;
import com.spring.rest.index.controller.IndexController;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventTarget;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    private final EventValidator eventValidator;

    @GetMapping
    public ResponseEntity getEventsList(Pageable page, PagedResourcesAssembler<Event> assembler){

        Page<Event> list = eventRepository.findAll(page);
        PagedModel<EntityModel<Event>> entityModels = assembler.toModel(list,
                entity -> EntityModel.of(entity).add(linkTo(EventController.class).slash(entity.getId()).withSelfRel()));
        entityModels.add(Link.of("/docs/index.html").withRel("profile"));

        return ResponseEntity.status(HttpStatus.OK).body(entityModels);
    }

    @GetMapping("{id}")
    public ResponseEntity getEvent(@PathVariable Integer id){

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(!optionalEvent.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EntityModel entityModel = EntityModel.of(optionalEvent.get());
        entityModel.add(Link.of("/docs/index.html").withRel("profile"));
        entityModel.add(linkTo(methodOn(EventController.class).getEvent(id)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    public ResponseEntity createEvents(@RequestBody @Valid EventDto eventDto, Errors errors){

        ModelMapper modelMapper = new ModelMapper();

        if (errors.hasErrors()){
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);

        if (errors.hasErrors()){
            return  badRequest(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event save = eventRepository.save(event);

        URI uri = linkTo(EventController.class).slash(save.getId()).toUri();

        EntityModel<Event> eventEntityModel = EntityModel.of(event);
        eventEntityModel.add(linkTo(methodOn(EventController.class).createEvents(eventDto, errors)).withSelfRel());
        eventEntityModel.add(linkTo(methodOn(EventController.class).queryEvents()).withRel("query-events"));
        eventEntityModel.add(linkTo(methodOn(EventController.class).updateEvents()).withRel("update-events"));
        eventEntityModel.add(Link.of("/docs/index.html").withRel("profile"));

        return ResponseEntity.created(uri).body(eventEntityModel);
    }

    private static ResponseEntity< EntityModel<Errors>> badRequest(Errors errors) {

        EntityModel<Errors> errorsEntityModel = EntityModel
                .of(errors, linkTo(methodOn(IndexController.class).index()).withRel("index"));

        System.out.println("errorsEntityModel = " + errorsEntityModel);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsEntityModel);
    }

    @GetMapping("query-events")
    public ResponseEntity queryEvents() {

        Event event = new Event();

        EntityModel<Event> eventEntityModel = EntityModel.of(event);
        eventEntityModel.add(linkTo(methodOn(EventController.class).queryEvents()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(eventEntityModel);
    }

    @GetMapping("update-events")
    public ResponseEntity updateEvents(){

        Event event = new Event();

        EntityModel<Event> eventEntityModel = EntityModel.of(event);
        eventEntityModel.add(linkTo(methodOn(EventController.class).queryEvents()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(eventEntityModel);
    }
}
