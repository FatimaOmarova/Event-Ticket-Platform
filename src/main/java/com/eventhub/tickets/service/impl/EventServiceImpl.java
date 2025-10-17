package com.eventhub.tickets.service.impl;

import com.eventhub.tickets.domain.CreateEventRequest;
import com.eventhub.tickets.domain.UpdateEventRequest;
import com.eventhub.tickets.domain.UpdateTicketTypeRequest;
import com.eventhub.tickets.domain.entity.Event;
import com.eventhub.tickets.domain.entity.TicketType;
import com.eventhub.tickets.domain.entity.User;
import com.eventhub.tickets.exceptions.EventUpdateException;
import com.eventhub.tickets.exceptions.TicketTypeNotFoundException;
import com.eventhub.tickets.exceptions.UserNotFoundException;
import com.eventhub.tickets.repository.EventRepository;
import com.eventhub.tickets.repository.UserRepository;
import com.eventhub.tickets.service.EventService;
import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id %s not found", organizerId))
                );

        Event eventToCreate = new Event();

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);
                    return ticketTypeToCreate;
                }).toList();

        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSaleStart(event.getSalesStart());
        eventToCreate.setSaleEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsByOrganizer(UUID organizerId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizerId, pageable);
    }

    @Override
    public Optional<Event> getEventForOrganizer(UUID organizerId, UUID id) {
        return eventRepository.findByIdAndOrganizerId(id, organizerId);
    }

    @Override
    @Transactional
    public Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event) {
        if(event.getId() == null){
            throw new EventUpdateException("Event ID cannot be null");
        }
        if(!id.equals(event.getId())){
            throw new EventUpdateException("Cannot update the ID of an event");
        }

        Event existingEvent = eventRepository.findByIdAndOrganizerId(id, organizerId)
                .orElseThrow(() -> new EventUpdateException(String.format("Event with ID '%s' does not exist", id))
                );
        existingEvent.setName(event.getName());
        existingEvent.setStart(event.getStart());
        existingEvent.setEnd(event.getEnd());
        existingEvent.setVenue(event.getVenue());
        existingEvent.setSaleStart(event.getSalesStart());
        existingEvent.setSaleEnd(event.getSalesEnd());
        existingEvent.setStatus(event.getStatus());

        Set<UUID> requestTicketTypeIds = event.getTicketTypes()
                .stream()
                .map(UpdateTicketTypeRequest::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        existingEvent.getTicketTypes().removeIf(existingTicketType ->
                !requestTicketTypeIds.contains(existingTicketType.getId())
        );

        Map<UUID, TicketType> existingTicketTypesIndex = existingEvent.getTicketTypes().stream()
                .collect(Collectors.toMap(TicketType::getId, Function.identity()));
        for (UpdateTicketTypeRequest ticketType : event.getTicketTypes()) {
            if(ticketType.getId() == null) {
                //create
            }else if(existingTicketTypesIndex.containsKey(ticketType.getId())){
                    //update
                }
            else{
            throw new TicketTypeNotFoundException(String.format("Ticket type with ID '%s' not found in event with ID '%s'", ticketType.getId()
        ));

            }
        }
        return eventRepository.save(existingEvent);
    }
}
