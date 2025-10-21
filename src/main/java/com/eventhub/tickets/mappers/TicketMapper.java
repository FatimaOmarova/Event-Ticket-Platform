package com.eventhub.tickets.mappers;

import com.eventhub.tickets.domain.dto.ListTicketResponseDto;
import com.eventhub.tickets.domain.dto.ListTicketTicketTypeResponseDto;
import com.eventhub.tickets.domain.entity.Ticket;
import com.eventhub.tickets.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketTicketTypeResponseDto toListTicketTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);
}
