package net.SumayaIbrahim.bets.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ticket_tiers")
public class TicketTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_tier_id")
    private long ticketTierID;
    @Column(name = "ticket_type")
    private String ticketType;
    @Column(name = "ticket_price")
    private float ticketPrice;
    @Column(name = "available_tickets")
    private int availableTickets;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
