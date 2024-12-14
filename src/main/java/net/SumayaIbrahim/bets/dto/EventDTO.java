package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.EventReview;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.entity.WaitingList;

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
    private WaitingListDTO waitingList;
    private List<EventReview> eventReviews;

    public void addReview(EventReview eventReview)
    {
        eventReviews.add(eventReview);
    }

}
