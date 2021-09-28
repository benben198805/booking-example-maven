package com.ben.bookingexamplemaven.infrastructure.repository;

import com.ben.bookingexamplemaven.domain.model.Order;
import com.ben.bookingexamplemaven.domain.repository.OrderRepository;
import com.ben.bookingexamplemaven.infrastructure.mapper.EntityToModelMapper;
import com.ben.bookingexamplemaven.infrastructure.persistence.OrderJpaPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaPersistence persistence;

    @Override
    public Optional<Order> findOrderById(Long id) {
        return persistence.findById(id).map(EntityToModelMapper.INSTANCE::mapToModel);
    }
}
