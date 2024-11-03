package net.SumayaIbrahim.bets.service.EventImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.repository.EventRepository;
import net.SumayaIbrahim.bets.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor


public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = modelMapper.map(eventDTO,Event.class);
        Event savedEvent = eventRepository.save(event);
        EventDTO savedEventDTO = modelMapper.map(savedEvent, EventDTO.class);
        return savedEventDTO;
    }

    @Override
    public List<EventDTO> GetAllEvents() {
        // Basically getting or "fetching" all the events from the database
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> modelMapper.map(event,EventDTO.class)).collect(Collectors.toList());


    }
}
