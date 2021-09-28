package com.ben.bookingexamplemaven.presentation.dto.response.eticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ben.bookingexamplemaven.domain.model.enums.ETicketStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ETicketResponse {
    private Long id;

    private Long orderId;

    private ETicketStatus status;

    private String callbackId;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
