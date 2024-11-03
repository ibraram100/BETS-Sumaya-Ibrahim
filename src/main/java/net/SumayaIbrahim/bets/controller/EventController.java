package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController // This class is able to handle http requests
@RequestMapping("/api/events")
public class EventController {
    private EventService eventService;
    @PostMapping("/create")
    // Basically receving an event and storing it in the db
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO)
    {
        EventDTO savedEvent = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Sending all the published events
    @GetMapping("/allevents")
    public ResponseEntity<List<EventDTO>> getAllEvents(){
        List<EventDTO> events = eventService.GetAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);

    }
}
