package com.eventhub.tickets.service;

import com.eventhub.tickets.domain.CreateEventRequest;
import com.eventhub.tickets.domain.entity.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
