package com.eventhub.tickets.service;

import com.eventhub.tickets.domain.CreateEventRequest;
import com.eventhub.tickets.domain.UpdateEventRequest;
import com.eventhub.tickets.domain.entity.Event;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
    Page<Event> listEventsByOrganizer(UUID organizerId, Pageable pageable);
    Optional<Event> getEventForOrganizer(UUID organizerId, UUID id);
    Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event);
    void deleteEventForOrganizer(UUID organizerId, UUID id);
}
