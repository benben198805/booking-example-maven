package com.ben.bookingexamplemaven.infrastructure.persistence;

import com.ben.bookingexamplemaven.infrastructure.dataentity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaPersistence extends JpaRepository<OrderEntity, Long> {
}
