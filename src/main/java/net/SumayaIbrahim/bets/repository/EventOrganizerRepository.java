package net.SumayaIbrahim.bets.repository;

import lombok.Setter;
import net.SumayaIbrahim.bets.entity.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventOrganizerRepository extends JpaRepository<EventOrganizer,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}
