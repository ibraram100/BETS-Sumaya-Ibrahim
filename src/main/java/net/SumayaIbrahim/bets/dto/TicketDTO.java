package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
