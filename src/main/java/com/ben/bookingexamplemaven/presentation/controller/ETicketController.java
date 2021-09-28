package com.ben.bookingexamplemaven.presentation.controller;

import com.ben.bookingexamplemaven.application.ETicketApplicationService;
import com.ben.bookingexamplemaven.presentation.assembler.ModelToResponseMapper;
import com.ben.bookingexamplemaven.presentation.dto.response.eticket.ETicketResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.presentation.dto.command.CreateETicketCommand;

import javax.validation.Valid;

@RestController
@RequestMapping("/booking-orders")
@Slf4j
@Validated
@AllArgsConstructor
public class ETicketController {
    private final ETicketApplicationService applicationService;

    @PostMapping("/{oid}/e-tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public ETicketResponse create(
            @PathVariable(value = "oid") Long orderId,
            @Valid @RequestBody CreateETicketCommand command) {
        ETicket eTicket = applicationService.create(orderId, command.getFlight(),
                command.getUserName(), command.getUserID());

        return ModelToResponseMapper.INSTANCE.mapToETicketResponse(eTicket);
    }
}
