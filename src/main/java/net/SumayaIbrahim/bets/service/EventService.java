package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.EventDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO getEventById(Long eventID);
    EventDTO updateEvent(EventDTO eventDTO);
    void deleteEvent(Long eventId);
    List<EventDTO> GetAllEvents();
    List<EventDTO> getEventsByUserId(Long userId);
}
