package com.spring.rest.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.common.RestDocsConfiguration;
import com.spring.rest.common.TestDescription;
import com.spring.rest.events.dto.EventDto;
import com.spring.rest.events.domain.Event;
import com.spring.rest.events.dto.EventUpdateDto;
import com.spring.rest.events.enums.EventStatus;
import com.spring.rest.events.repository.EventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@Transactional
@ActiveProfiles("test")
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName("정상적으로 이벤트를 생성하는 테스트")
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
                .eventStatus(EventStatus.DRAFT)
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("free").value(false))
                .andExpect(jsonPath("offline").value(true))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
                .andDo(document("create-event",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-events").description("link to query events"),
                                linkWithRel("update-events").description("link to update an exisiting"),
                                linkWithRel("profile").description("link to profile")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
                        ),
                        requestFields(
                                fieldWithPath("name").description("Name of new event"),
                                fieldWithPath("description").description("description of new event"),
                                fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
                                fieldWithPath("closeEnrollmentDateTime").description("date time of close of new event"),
                                fieldWithPath("beginEnrollmentDateTime").description("date time of begin of new event"),
                                fieldWithPath("endEventDateTime").description("date time of end of new event"),
                                fieldWithPath("location").description("location of new event"),
                                fieldWithPath("basePrice").description("base price of new event"),
                                fieldWithPath("maxPrice").description("max price of new event"),
                                fieldWithPath("limitOfEnrollment").description("limit of Enrollment"),
                                fieldWithPath("eventStatus").description("event status")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identifier of new event"),
                                fieldWithPath("name").description("Name of new event"),
                                fieldWithPath("description").description("description of new event"),
                                fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
                                fieldWithPath("closeEnrollmentDateTime").description("date time of close of new event"),
                                fieldWithPath("beginEnrollmentDateTime").description("date time of begin of new event"),
                                fieldWithPath("endEventDateTime").description("date time of end of new event"),
                                fieldWithPath("location").description("location of new event"),
                                fieldWithPath("basePrice").description("base price of new event"),
                                fieldWithPath("maxPrice").description("max price of new event"),
                                fieldWithPath("limitOfEnrollment").description("limit of Enrollment"),
                                fieldWithPath("free").description("it tells if this event is free or not"),
                                fieldWithPath("offline").description("it tells if this event is offline event or not"),
                                fieldWithPath("eventStatus").description("event status"),
                                fieldWithPath("_links.self.href").description("link to self"),
                                fieldWithPath("_links.query-events.href").description("link to query events"),
                                fieldWithPath("_links.update-events.href").description("link to update events"),
                                fieldWithPath("_links.profile.href").description("link to profile")
                        )

                ))
        ;
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

        EventDto event = EventDto.builder()
                .name("Spring")
                .description("Rest Api Development with Spring")
                .beginEventDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .endEventDateTime(LocalDateTime.of(2022, 9, 21, 12, 12))
                .basePrice(200)
                .maxPrice(100)
                .limitOfEnrollment(100)
                .location("선릉역 위워크")
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors[0].objectName").exists())
                .andExpect(jsonPath("errors[0].filed").exists())
                .andExpect(jsonPath("errors[0].defaultMessage").exists())
                .andExpect(jsonPath("errors[0].code").exists())
                .andExpect(jsonPath("errors[0].rejectValue").exists())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }

    @Test
    @DisplayName("30개의 이벤트를 10개씩 두번째 페이지 조회하기")
    public void queryEvents() throws Exception {

        IntStream.range(0, 30).forEach(i -> {
            generateEvent(i);
        });

        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "id,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.eventList[0]._links.self").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("query-events"))
        ;
    }

    private Event generateEvent(int i) {

        Event event = Event.builder()
                .name("event" + i)
                .description("test event")
                .build();

        return eventRepository.save(event);
    }

    @Test
    @DisplayName("기존의 이벤트를 하나 조회하기")
    public void getEventTest() throws Exception {

        Event event = generateEvent(100);

        mockMvc.perform(get("/api/events/{id}", event.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("get-an-event"))
        ;
    }

    @Test
    @DisplayName("없는 이벤트를 조회했을 떄 404 응답받기")
    public void getEventTestError() throws Exception {

        mockMvc.perform(get("/api/events/1008"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    @DisplayName("이벤트 수정시 잘못된 데이터를 입력하였을때 400 응답받기")
    public void updateEventTestBadRequest() throws Exception {

        Event event = generateEvent(1);

        EventUpdateDto data = EventUpdateDto.builder()
                .name(event.getName())
                .build();

        mockMvc.perform(put("/api/events/{id}", event.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(data)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("이벤트 수정시 이벤트가 없는 경우 404 응답받기")
    public void updateEventTestNotFound() throws Exception {

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
                .build();

        mockMvc.perform(put("/api/events/1008")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }

    @Test
    @DisplayName("정상적으로 이벤트가 수정되었을시")
    public void updateEventTest() throws Exception {

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
                .build();

        Event data = eventRepository.save(event);
        data.setName("Spring Update Event");
        data.setDescription("Rest Api Development");

        mockMvc.perform(put("/api/events/{id}", data.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(data)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("update-envent"))
        ;

    }
}