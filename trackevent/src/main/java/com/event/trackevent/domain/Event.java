package com.event.trackevent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String eventId;
    private String startState;
    private String endState;
    private Long startTime;
    private Long endTime;
    private Long duration;
    private String host;
    private String type;
    private Boolean flag;
}
