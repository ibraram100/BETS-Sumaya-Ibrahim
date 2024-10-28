package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
     private EventOrganizer eventOrganizer;
}
