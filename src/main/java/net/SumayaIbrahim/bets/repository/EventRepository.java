package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByUserId(Long userId);

}
