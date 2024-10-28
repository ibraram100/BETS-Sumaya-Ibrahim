package net.SumayaIbrahim.bets.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "event_organizers")
public class EventOrganizer extends User{
    @OneToMany(mappedBy = "eventOrganizer", cascade = CascadeType.ALL) // event could have multiple TicketTiers
    private List<Event> events = new ArrayList<>();
}
