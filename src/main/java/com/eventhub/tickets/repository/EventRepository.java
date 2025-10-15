package com.eventhub.tickets.repository;

import com.eventhub.tickets.domain.entity.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
