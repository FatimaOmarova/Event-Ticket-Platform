package com.eventhub.tickets.service;

import com.eventhub.tickets.domain.entity.QrCode;
import com.eventhub.tickets.domain.entity.Ticket;

import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);

}
