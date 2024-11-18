package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.TicketTier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EventDTO {
    private long eventID;
    private String eventName;
    private Date eventDate;
    private String location;
    private List<TicketTier> ticketTiers = new ArrayList<>();
    private List<TicketDTO> tickets;
    private Long userId;

}
