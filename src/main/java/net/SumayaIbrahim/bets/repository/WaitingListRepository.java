package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
    WaitingList findById(long id);
    WaitingList findByEventEventID(long eventId);
}
