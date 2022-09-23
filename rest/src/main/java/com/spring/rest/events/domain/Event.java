package com.spring.rest.events.domain;

import com.spring.rest.events.enums.EventStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private boolean offline;
    private boolean free;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    public void update() {
        if(this.basePrice == 0 && this.maxPrice == 0){
            this.free = true;
        }else{
            this.free = false;
        }

        if(this.location == null || this.location.equals("")){
            this.offline = false;
        }else{
            this.offline = true;
        }

    }
}
