package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
     @Id // it's like saying hey! that's a primary key!
     @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incrementing of the eventID
     @Column(name = "event_id")
     private long eventID;
     @Column(name = "event_name")
     private String eventName;
     @Column(name = "event_date")
     private Date eventDate;
     private String location;
     @OneToMany(mappedBy = "event", cascade = CascadeType.ALL) // event could have multiple TicketTiers
     private List<TicketTier> ticketTiers = new ArrayList<>();
     @ManyToOne // event can have only one EventOrganizer
     @JoinColumn(name = "event_org_id")
     private User user;

     @OneToMany(mappedBy = "event", cascade = CascadeType.ALL , orphanRemoval = true)
     private List<Ticket> tickets = new ArrayList<>();

     // Each event have one waiting List
     @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private WaitingList waitingList;

     @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
     private List<EventReview> eventReviews;

     public void addReview(EventReview eventReview)
     {
          eventReviews.add(eventReview);
     }


}
