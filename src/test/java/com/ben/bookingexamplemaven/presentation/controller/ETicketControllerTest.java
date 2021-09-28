package com.ben.bookingexamplemaven.presentation.controller;

import com.ben.bookingexamplemaven.application.ETicketApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ben.bookingexamplemaven.presentation.dto.command.CreateETicketCommand;
import com.ben.bookingexamplemaven.presentation.exception.NotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ETicketControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ETicketApplicationService applicationService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_create_eTicket() throws Exception {
        // given
        String flight = "CA123";
        String userName = "user-name";
        String userId = "user-id";
        CreateETicketCommand command = CreateETicketCommand.builder().flight(flight)
                                                           .userID(userId).userName(userName).build();

        // when
        mvc.perform(
                post("/booking-orders/1/e-tickets")
                        .content(objectMapper.writeValueAsString(command))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isCreated());

        // then
        verify(applicationService, times(1)).create(eq(1L),
                eq(flight), eq(userName), eq(userId));
    }

    @Test
    public void should_throw_exception_when_validate_order_exist_fail() throws Exception {
        // given
        String flight = "CA123";
        String userName = "user-name";
        String userId = "user-id";
        CreateETicketCommand command = CreateETicketCommand.builder().flight(flight)
                                                           .userID(userId).userName(userName).build();

        when(applicationService.create(any(), any(), any(), any()))
                .thenThrow(new NotFoundException("NOT_FOUND_ORDER", String.format("order id: %d not found", 1)));

        // when
        mvc.perform(
                post("/booking-orders/1/e-tickets")
                        .content(objectMapper.writeValueAsString(command))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("$.code").value("NOT_FOUND_ORDER"));

        // then
    }

}
