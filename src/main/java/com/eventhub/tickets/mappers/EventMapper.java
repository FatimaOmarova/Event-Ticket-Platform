package com.eventhub.tickets.mappers;

import com.eventhub.tickets.domain.CreateEventRequest;
import com.eventhub.tickets.domain.CreateTicketTypeRequest;
import com.eventhub.tickets.domain.dto.CreateEventRequestDto;
import com.eventhub.tickets.domain.dto.CreateEventResponseDto;
import com.eventhub.tickets.domain.dto.CreateTicketTypeRequestDto;
import com.eventhub.tickets.domain.entity.Event;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

}
