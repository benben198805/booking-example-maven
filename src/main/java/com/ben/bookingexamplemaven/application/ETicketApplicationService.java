package com.ben.bookingexamplemaven.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ben.bookingexamplemaven.client.AirportInfoServiceClient;
import com.ben.bookingexamplemaven.client.model.SettingsDto;
import com.ben.bookingexamplemaven.client.model.SettingsResponse;
import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.model.enums.ETicketStatus;
import com.ben.bookingexamplemaven.domain.repository.ETicketRepository;
import com.ben.bookingexamplemaven.domain.repository.OrderRepository;
import com.ben.bookingexamplemaven.presentation.exception.NotFoundException;


@Service
@RequiredArgsConstructor
public class ETicketApplicationService {
    private final ETicketRepository repository;
    private final OrderRepository orderRepository;
    private final AirportInfoServiceClient airportInfoServiceClient;

    public ETicket create(Long orderId, String flightId, String userName, String userID) {
        orderRepository.findOrderById(orderId)
                       .orElseThrow(() ->
                               new NotFoundException("NOT_FOUND", String.format("order id: %d not found", orderId)));

        SettingsDto settingsDto = SettingsDto.builder().userName(userName).userID(userID).build();
        SettingsResponse settingsResponse;
        try {
            settingsResponse = airportInfoServiceClient.takeSettings(flightId, settingsDto);
        } catch (Exception e) {
            ETicket eTicket = ETicket.init(orderId);
            eTicket.setStatus(ETicketStatus.FAIL);
            return this.repository.create(eTicket);
        }

        ETicket eTicket = ETicket.init(orderId);
        eTicket.setCallbackId(settingsResponse.getCallbackId());

        return this.repository.create(eTicket);
    }
}
