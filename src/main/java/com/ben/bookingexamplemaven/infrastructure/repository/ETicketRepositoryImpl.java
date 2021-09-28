package com.ben.bookingexamplemaven.infrastructure.repository;

import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.repository.ETicketRepository;
import com.ben.bookingexamplemaven.infrastructure.dataentity.ETicketEntity;
import com.ben.bookingexamplemaven.infrastructure.mapper.EntityToModelMapper;
import com.ben.bookingexamplemaven.infrastructure.mapper.ModelToEntityMapper;
import com.ben.bookingexamplemaven.infrastructure.persistence.ETicketJpaPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ETicketRepositoryImpl implements ETicketRepository {
    private final ETicketJpaPersistence persistence;

    @Override
    public ETicket create(ETicket eTicket) {
        ETicketEntity savedETicketEntity = this.persistence.save(ModelToEntityMapper.INSTANCE.mapToEntity(eTicket));
        return EntityToModelMapper.INSTANCE.mapToModel(savedETicketEntity);
    }
}
