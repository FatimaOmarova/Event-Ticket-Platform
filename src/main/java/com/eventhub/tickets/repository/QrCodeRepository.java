package com.eventhub.tickets.repository;

import com.eventhub.tickets.domain.entity.QrCode;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
}
