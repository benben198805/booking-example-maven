package com.ben.bookingexamplemaven.presentation.assembler;

import com.ben.bookingexamplemaven.presentation.dto.response.eticket.ETicketResponse;
import com.ben.bookingexamplemaven.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.ben.bookingexamplemaven.domain.model.ETicket;

import java.time.LocalDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModelToResponseMapper {

    ModelToResponseMapper INSTANCE = Mappers.getMapper(ModelToResponseMapper.class);

    @Mapping(target = "createdTime", source = "model.createdTime", qualifiedByName = "toTimestamp")
    @Mapping(target = "updatedTime", source = "model.createdTime", qualifiedByName = "toTimestamp")
    ETicketResponse mapToETicketResponse(ETicket model);

    @Named("toTimestamp")
    default long toTimestamp(LocalDateTime localDateTime) {
        return DateUtils.toTimestamp(localDateTime);
    }
}
