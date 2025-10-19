package com.eventhub.tickets.mappers;

import com.eventhub.tickets.domain.CreateEventRequest;
import com.eventhub.tickets.domain.CreateTicketTypeRequest;
import com.eventhub.tickets.domain.dto.CreateEventRequestDto;
import com.eventhub.tickets.domain.dto.CreateEventResponseDto;
import com.eventhub.tickets.domain.dto.CreateTicketTypeRequestDto;
import com.eventhub.tickets.domain.dto.GetEventDetailsResponseDto;
import com.eventhub.tickets.domain.dto.GetEventTicketTypesResponseDto;
import com.eventhub.tickets.domain.dto.ListEventResponseDto;
import com.eventhub.tickets.domain.dto.ListEventTicketTypeResponseDto;
import com.eventhub.tickets.domain.entity.Event;

import com.eventhub.tickets.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);

    GetEventTicketTypesResponseDto toGetEventTicketTypesResponseDto(TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

    UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);

    UpdateEventRequest fromDto(UpdateEventRequestDto dto);

    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

}
