package net.SumayaIbrahim.bets.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.Event;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTierDTO {
    private long ticketTierID;
    private String ticketType;
    private float ticketPrice;
    private int availableTickets;
    private long eventID;
}
