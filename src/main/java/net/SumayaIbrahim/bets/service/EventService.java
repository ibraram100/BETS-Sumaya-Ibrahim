package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.EventDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    List<EventDTO> GetAllEvents();
}
