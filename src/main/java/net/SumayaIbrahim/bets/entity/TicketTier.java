package net.SumayaIbrahim.bets.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class TicketTier {

    private long ticketTierID;
    private String ticketType;
    private float ticketPrice;
    private int availableTickets;
}
