package com.eventhub.tickets.service.impl;

import com.eventhub.tickets.domain.entity.Ticket;
import com.eventhub.tickets.domain.entity.TicketType;
import com.eventhub.tickets.domain.entity.User;
import com.eventhub.tickets.domain.enums.TicketStatusEnum;
import com.eventhub.tickets.exceptions.TicketSoldOutException;
import com.eventhub.tickets.exceptions.TicketTypeNotFoundException;
import com.eventhub.tickets.exceptions.UserNotFoundException;
import com.eventhub.tickets.repository.TicketRepository;
import com.eventhub.tickets.repository.TicketTypeRepository;
import com.eventhub.tickets.repository.UserRepository;
import com.eventhub.tickets.service.QrCodeService;
import com.eventhub.tickets.service.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userID, UUID ticketTypeId) {
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(
                String.format("User with ID %s was not found", userID)
        ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId).orElseThrow(() -> new TicketTypeNotFoundException(
                String.format("Ticket type with ID %s was not found", ticketTypeId)
        ));
        int purchasedTickets = ticketRepository.countByTicketTypeId((ticketType.getId()));
        Integer totalAvailable = ticketType.getTotalAvailable();

        if(purchasedTickets + 1 > totalAvailable){
            throw new TicketSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }
}
