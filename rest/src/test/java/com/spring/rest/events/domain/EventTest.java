package com.spring.rest.events.domain;

import com.spring.rest.domain.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 100, false",
            "100, 0, false",
    })
    public void testFree(int basePrice, int maxPrice, boolean check) throws Exception {

        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        event.update();

        assertThat(event.isFree()).isEqualTo(check);
    }

    @ParameterizedTest
    @MethodSource("isOffline")
    public void testOffline(String location, boolean check) throws Exception {

        Event event = Event.builder()
                .location(location)
                .build();
        event.update();

        assertThat(event.isOffline()).isEqualTo(check);
    }

    private static Stream<Arguments> isOffline() {
        return Stream.of(
                Arguments.of("잠심 종합경기장", true),
                Arguments.of(null, false),
                Arguments.of("", false)
        );
    }
}