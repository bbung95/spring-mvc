package com.spring.rest.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.events.domain.Event;
import com.spring.rest.events.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void createEventsTest() throws Exception {

        Event event = Event.builder()
                .name("Spring")
                .description("Rest Api Development with Spring")
                .beginEventDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .endEventDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("선릉역 위워크")
                .id(100)
                .build();

        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }
}