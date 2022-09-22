package com.spring.rest.events.dto;

import com.spring.rest.events.enums.EventStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;                        // (Optional)
    private int basePrice;                          // (Optional)
    private int maxPrice;                           // (Optional)
    private int limitOfEnrollment;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

}
