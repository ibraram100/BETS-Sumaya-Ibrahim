package net.SumayaIbrahim.bets.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.TicketTier;

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
