package com.spring.rest.events.dto;

import com.spring.rest.events.enums.EventStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private LocalDateTime beginEnrollmentDateTime;
    @NotNull
    private LocalDateTime closeEnrollmentDateTime;
    @NotNull
    private LocalDateTime beginEventDateTime;
    @NotNull
    private LocalDateTime endEventDateTime;
    private String location; // (Optional)
    @Min(0)
    private int basePrice;                          // (Optional)
    @Min(0)
    private int maxPrice;                           // (Optional)
    @Min(0)
    private int limitOfEnrollment;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

}
