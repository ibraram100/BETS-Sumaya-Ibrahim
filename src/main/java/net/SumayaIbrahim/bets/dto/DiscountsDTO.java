package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.Ticket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountsDTO {
    private long discount_id;
    private String discount_type;
    private float discount_amount;
    private long ticketId;
}
