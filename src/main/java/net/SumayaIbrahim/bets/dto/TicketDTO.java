package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {
    private Long id;
    boolean isSold;
    boolean isExpired;
    private Long ticketTierId;
    private Long userId;
    private Date purchaseDate;
    private Long eventId;
}
