package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(long userId);
}
