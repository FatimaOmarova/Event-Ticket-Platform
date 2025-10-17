package com.eventhub.tickets.repository;

import com.eventhub.tickets.domain.entity.Event;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Page<Event> findByOrganizerId(UUID organizerId, Pageable pageable);
    Optional<Event> findByIdAndOrganizerId(UUID id, UUID organizerId);
}
