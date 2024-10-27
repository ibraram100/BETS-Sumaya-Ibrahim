package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EventOrganizer extends User {
    @OneToMany(mappedBy = "eventOrganizer", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();
}
