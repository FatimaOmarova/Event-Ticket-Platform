package com.eventhub.tickets.service;

import com.eventhub.tickets.domain.entity.QrCode;
import com.eventhub.tickets.domain.entity.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
}
