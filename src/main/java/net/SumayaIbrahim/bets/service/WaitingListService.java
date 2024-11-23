package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.WaitingListDTO;
import net.SumayaIbrahim.bets.entity.WaitingList;

public interface WaitingListService {
    WaitingListDTO createWaitingList(WaitingListDTO waitingListDTO);
    WaitingList findWaitingListById(long id);
    void addUserToWaitingList(long userId, long waitingListId);
    WaitingList findWaitingListByEventId(long eventId);
    void updateWaitingList(WaitingListDTO waitingListDTO);

}
