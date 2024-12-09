package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.TicketTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTierRepository extends JpaRepository<TicketTier, Long> {

//    List<TicketTier> findByEventId(long eventId);
    List<TicketTier> findByEventEventID(Long eventId);
}
