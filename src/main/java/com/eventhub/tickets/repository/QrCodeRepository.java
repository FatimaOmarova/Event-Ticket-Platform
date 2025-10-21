package com.eventhub.tickets.repository;

import com.eventhub.tickets.domain.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
    Optional<QrCode> findByTicketIdAndPurchaseId(UUID ticketId, UUID ticketPurchaseId);
}
