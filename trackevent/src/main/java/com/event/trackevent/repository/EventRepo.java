package com.event.trackevent.repository;

import com.event.trackevent.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, String> {
    Event findByEventId(String eventId);
}
