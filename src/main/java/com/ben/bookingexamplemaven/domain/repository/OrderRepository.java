package com.ben.bookingexamplemaven.domain.repository;

import com.ben.bookingexamplemaven.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findOrderById(Long id);
}
