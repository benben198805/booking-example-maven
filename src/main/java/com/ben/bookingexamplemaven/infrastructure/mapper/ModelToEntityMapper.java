package com.ben.bookingexamplemaven.infrastructure.mapper;

import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.model.Order;
import com.ben.bookingexamplemaven.infrastructure.dataentity.ETicketEntity;
import com.ben.bookingexamplemaven.infrastructure.dataentity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModelToEntityMapper {
    ModelToEntityMapper INSTANCE = Mappers.getMapper(ModelToEntityMapper.class);

    ETicketEntity mapToEntity(ETicket eTicket);

    OrderEntity mapToEntity(Order order);
}
