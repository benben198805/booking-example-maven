package com.ben.bookingexamplemaven.domain.model;

import com.ben.bookingexamplemaven.domain.model.enums.ETicketStatus;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ETicketTest {

    @Test
    public void should_init_e_ticket() {
        // given
        long orderId = 1L;

        // when
        ETicket eTicket = ETicket.init(orderId);

        // then
        assertEquals(orderId, (long) eTicket.getOrderId());
        Assert.assertEquals(ETicketStatus.PENDING, eTicket.getStatus());
    }

}
