package com.ben.bookingexamplemaven.infrastructure.persistence;

import com.ben.bookingexamplemaven.infrastructure.dataentity.ETicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ETicketJpaPersistence extends JpaRepository<ETicketEntity, Long> {
}
