package com.spring.rest.events.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder(){

        Event event = Event.builder()
                .name("Infearn Spring REST API")
                .description("REST API development whit Spring")
                .build();

        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        //String
        String name = "Event";
        String description = "Spring";

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);


    }
}