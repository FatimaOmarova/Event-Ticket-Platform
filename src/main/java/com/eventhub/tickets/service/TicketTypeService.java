package com.eventhub.tickets.service;

import com.eventhub.tickets.domain.entity.Ticket;

import java.util.UUID;

public interface TicketTypeService {
    Ticket purchaseTicket(UUID userID, UUID ticketTypeId);
}
