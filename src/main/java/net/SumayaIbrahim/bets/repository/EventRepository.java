package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

}
