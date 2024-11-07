package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
