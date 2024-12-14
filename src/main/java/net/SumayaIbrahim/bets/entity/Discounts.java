package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Discounts")
public class Discounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private long discount_id;
    @Column(name="discount_type")
    private String discount_type;
    @Column(name= "discount_amount")
    private float discount_amount;
    @ManyToOne //discounts can be applied to any ticket
    @JoinColumn(name= "tickets")
    private Ticket ticket;
}
