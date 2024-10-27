package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Entity
@Table(name = "events")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long eventID;
     @Column(name = "")
     private long eventOrganizerID;
     private String eventName;
     private Date eventDate;
     private String location;
}
