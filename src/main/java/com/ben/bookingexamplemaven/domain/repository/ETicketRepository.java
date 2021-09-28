package com.ben.bookingexamplemaven.domain.repository;

import com.ben.bookingexamplemaven.domain.model.ETicket;

public interface ETicketRepository {
    ETicket create(ETicket eTicket);
}
