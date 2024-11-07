package net.SumayaIbrahim.bets.service.EventImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.repository.EventRepository;
import net.SumayaIbrahim.bets.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        savedEventDTO.setEventID(savedEvent.getEventID());

        return savedEventDTO;
    }

    @Override
    public EventDTO getEventById(Long eventID) {
        Optional<Event> optionalEvent = eventRepository.findById(eventID); // we used optional because it might not return a thing

            Event event = optionalEvent.orElse(null);
            EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
            return eventDTO;


    }

    // Updating existing events
    @Override
    public EventDTO updateEvent(EventDTO eventDTO)
    {

        Optional<Event> optionalEvent = eventRepository.findById(eventDTO.getEventID());
        if (optionalEvent.isPresent()) {
            // Update the existing event with the new data
            Event existingEvent = optionalEvent.get();
            existingEvent = modelMapper.map(eventDTO, Event.class);

//            existingEvent.setEventName(event.getEventName());
//            existingEvent.setEventDate(event.getEventDate());
//            existingEvent.setLocation(event.getLocation());
            // ... update other fields as needed
            eventRepository.save(existingEvent);
        }
        else
        {
            // Handle the case where the event is not found
            return null;
        }
        return null;
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<EventDTO> GetAllEvents() {
        // Basically getting or "fetching" all the events from the database
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> modelMapper.map(event,EventDTO.class)).collect(Collectors.toList());


    }
}
