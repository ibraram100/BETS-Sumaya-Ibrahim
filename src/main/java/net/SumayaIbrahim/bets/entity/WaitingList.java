package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "waiting_lists")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // i spent a whole day trying to fix this problem, and the problem was I used Long instead of long as a type, i want to die
    @Column(name = "waiting_list_name")
    private String waitingListName;
    // Each waiting list can have one event only
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Waiting list could have multiple users
    @ManyToMany
    @JoinTable( name = "waiting_list_user", joinColumns = @JoinColumn(name = "waiting_list_id"), inverseJoinColumns = @JoinColumn(name = "user_id") )
    private List<User> users;

}
