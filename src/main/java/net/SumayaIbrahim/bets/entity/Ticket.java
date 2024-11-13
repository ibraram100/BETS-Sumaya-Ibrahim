package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    boolean isSold;
    boolean isExpired;
    @ManyToOne
    private TicketTier ticketTier;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // automatically populating the purchase date upon object creation
    // Automatically populating the purchase date upon object creation
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // Specify that this is a timestamp
    @CreationTimestamp
    private Date purchaseDate;

}