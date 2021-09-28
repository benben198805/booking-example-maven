package com.ben.bookingexamplemaven.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.model.Order;
import com.ben.bookingexamplemaven.infrastructure.dataentity.ETicketEntity;
import com.ben.bookingexamplemaven.infrastructure.dataentity.OrderEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityToModelMapper {

    EntityToModelMapper INSTANCE = Mappers.getMapper(EntityToModelMapper.class);

    ETicket mapToModel(ETicketEntity entity);

    Order mapToModel(OrderEntity entity);

}
