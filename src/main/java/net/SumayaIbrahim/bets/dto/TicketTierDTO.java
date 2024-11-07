package net.SumayaIbrahim.bets.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.entity.TicketTier;

import java.util.ArrayList;
import java.util.List;

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
    private List<Ticket> tickets = new ArrayList<>();

}
