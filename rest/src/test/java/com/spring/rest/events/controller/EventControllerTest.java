package com.spring.rest.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.common.TestDescription;
import com.spring.rest.events.dto.EventDto;
import com.spring.rest.events.domain.Event;
import com.spring.rest.events.enums.EventStatus;
import com.spring.rest.events.repository.EventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        EventDto event = EventDto.builder()
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
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @TestDescription("입력 받을 수 없는 값을 사용한 경우에 에러가 발생하는 테스트")
    public void createEventsTest_BAD_REQUEST() throws Exception {

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
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .id(100)
                .build();

//        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("입력 값이 비어있는 경우에 에러가 발생하는 테스트")
    public void createEvent_BAD_REQUEST_EMPTY_INPUT() throws Exception {

        EventDto event = EventDto.builder()
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("입력 값이 잘못된 경우에 에러가 발생하는 테스트")
    public void createEvent_BAD_REQUEST_WRONG_INPUT() throws Exception {

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
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}