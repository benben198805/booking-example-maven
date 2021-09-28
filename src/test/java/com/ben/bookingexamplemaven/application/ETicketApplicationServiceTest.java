package com.ben.bookingexamplemaven.application;

import com.ben.bookingexamplemaven.client.AirportInfoServiceClient;
import com.ben.bookingexamplemaven.client.model.SettingsResponse;
import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.model.Order;
import com.ben.bookingexamplemaven.domain.model.enums.ETicketStatus;
import com.ben.bookingexamplemaven.domain.repository.ETicketRepository;
import com.ben.bookingexamplemaven.domain.repository.OrderRepository;
import com.ben.bookingexamplemaven.presentation.exception.InternalServerException;
import com.ben.bookingexamplemaven.presentation.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ETicketApplicationServiceTest {
    @InjectMocks
    private ETicketApplicationService applicationService;

    @Mock
    private ETicketRepository repository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AirportInfoServiceClient airportInfoServiceClient;

    @Test
    public void should_create_eTicket() {
        // given
        String callbackId = "19282";
        ETicket build = ETicket.builder().orderId(1L).callbackId(callbackId).orderId(1L)
                               .status(ETicketStatus.PENDING).build();
        when(repository.create(any())).thenReturn(build);
        when(orderRepository.findOrderById(any())).thenReturn(Optional.of(Order.builder().build()));

        SettingsResponse response = SettingsResponse.builder().callbackId(callbackId).build();
        when(airportInfoServiceClient.takeSettings(any(), any())).thenReturn(response);

        // when
        ETicket eTicket = applicationService.create(1L, "AC83", "user-name", "user-id");

        // then
        verify(airportInfoServiceClient, times(1)).takeSettings(any(), any());
        Assert.assertEquals(ETicketStatus.PENDING, eTicket.getStatus());
        assertEquals(1L, (long) eTicket.getOrderId());
        assertEquals(callbackId, eTicket.getCallbackId());
    }

    @Test
    public void should_create_eTicket_without_callback_id_when_call_client_fail() {
        // given
        ETicket build = ETicket.builder().orderId(1L).orderId(1L)
                               .status(ETicketStatus.FAIL).build();
        when(repository.create(any())).thenReturn(build);
        when(orderRepository.findOrderById(any())).thenReturn(Optional.of(Order.builder().build()));

        Mockito.doThrow(new InternalServerException(400, "INTERNAL_SERVER_EXCEPTION", "internal exception"))
               .when(airportInfoServiceClient).takeSettings(any(), any());

        // when
        ETicket eTicket = applicationService.create(1L, "AC83", "user-name", "user-id");

        // then
        verify(airportInfoServiceClient, times(1)).takeSettings(any(), any());
        Assert.assertEquals(ETicketStatus.FAIL, eTicket.getStatus());
        assertEquals(1L, (long) eTicket.getOrderId());
        assertNull(eTicket.getCallbackId());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_order_id_not_exist() {
        // given
        when(orderRepository.findOrderById(any())).thenReturn(Optional.empty());

        // when
        applicationService.create(1L, "AC83", "user-name", "user-id");

        // then
    }
}
