package com.event.trackevent.services;

import com.event.trackevent.domain.Event;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);
    Event getEventById(String eventId);
    void updateEvent(Event event);
    void readFie(String filePath);
}
