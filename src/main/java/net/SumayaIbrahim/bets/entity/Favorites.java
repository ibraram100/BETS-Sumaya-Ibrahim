package net.SumayaIbrahim.bets.entity;



import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favorites")
public class Favorites {

    @Id //each event added to the 'favorites' list has its own ID
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    //Each user can have one 'favorites' list
    @OneToOne
    @JoinColumn(name= "user_id")
    private User user;
    //Each user can add multiple events to their 'favorites' ist
    @OneToMany
    @JoinTable(name="favorite_events", joinColumns = @JoinColumn(name = "favorite_events_id"), inverseJoinColumns = @JoinColumn(name = "event_id") )
    private List<Event> events;




}
