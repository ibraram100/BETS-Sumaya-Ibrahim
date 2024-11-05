package net.SumayaIbrahim.bets.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller // This class is able to handle http requests
@RequestMapping("/events")
public class EventController {
    private EventService eventService;
//    @PostMapping("/create")
    // Basically receving an event and storing it in the db
    // Probably i should delete this one
//    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO)
//    {
//        EventDTO savedEvent = eventService.createEvent(eventDTO);
//        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
//    }

    // Sending all the published events (works for api requests, probably gonna be removed)
    @GetMapping("/allevents")
    public ResponseEntity<List<EventDTO>> getAllEvents(){
        List<EventDTO> events = eventService.GetAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Listing all the events in a nice looking html page
    @GetMapping("/getevents")
    public String getAllEvents(Model model)
    {
        List<EventDTO> events = eventService.GetAllEvents();
        model.addAttribute("events", events);
        return "events"; // it will go to events.html in resources folder, it will also send events to that file

    }

    // Just taking the user to the create event form
    @GetMapping("create-event")
    public String showEventCreationForm(Model model, HttpSession session) // Session is used to map the event to it's creator's id
    {
        EventDTO eventDTO = new EventDTO();
        int userId = (int) session.getAttribute("userId");// Sending the user id
        model.addAttribute("event",eventDTO);
        model.addAttribute("userId",userId);
        return "create-event";
    }

    @PostMapping("save-event")
    public String saveEvent(@ModelAttribute("event") EventDTO eventDTO)
    {
        eventService.createEvent(eventDTO);
        return "redirect:/getevents";
    }










}
