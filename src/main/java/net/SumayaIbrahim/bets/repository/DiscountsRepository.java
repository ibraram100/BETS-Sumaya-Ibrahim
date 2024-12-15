package net.SumayaIbrahim.bets.repository;
import net.SumayaIbrahim.bets.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountsRepository extends JpaRepository<Discounts, Long>{
    Discounts findById (long id);
    List<Discounts> findByTicketID (long ticketId);
}
