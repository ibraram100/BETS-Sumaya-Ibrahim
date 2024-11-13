package net.SumayaIbrahim.bets.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.Role;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.EventRepository;
import net.SumayaIbrahim.bets.repository.RoleRepository;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.RoleService;
import net.SumayaIbrahim.bets.service.TicketTierService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@Controller // This class is able to handle http requests
@RequestMapping("/events")
public class EventController {
    private EventService eventService;
    private EventRepository eventRepo;
    private UserRepository userRepository;
    private TicketTierService tierService;
    private ModelMapper modelMapper;
    private RoleService roleService;



    // Listing all the events that were created by the user in a nice looking html page
    @GetMapping("/getevents")
    public String getAllEvents(Model model, Principal principal)
    {
        // Principal only stores the user email, so we get user id by creating user object and extracting the id later, it's not smart but i couldn't get sessions to work
        User user = userRepository.findByEmail(principal.getName());
        List<EventDTO> events = eventService.getEventsByUserId(user.getId());
        model.addAttribute("events", events);
        return "events"; // it will go to events.html in resources folder, it will also send events to that file
    }

    // Just taking the user to the create event form
    @GetMapping("create-event")
    public String showEventCreationForm(Model model, HttpSession session, Principal principal) // Session is used to map the event to it's creator's id
    {
        // we store the email address of the current logged in user who just enterd /events/create-event
        String userEmail = principal.getName();
        EventDTO eventDTO = new EventDTO();
        TicketTierDTO ticketTierDTO = new TicketTierDTO();
        // We make a user object based on the email of the user
        User user = userRepository.findByEmail(userEmail);
        // we did all of that so that we can send the userId to the event, because user id is a foreign key, and event can't be created without
        // it's basically like using a session, but with extra steps
        // i will probably implement proper sessions in the future
        eventDTO.setUserId(user.getId());
//        System.out.println(eventDTO.getUserId());
        model.addAttribute("event",eventDTO);
        model.addAttribute("tier", ticketTierDTO);
        return "create-event";
    }





    @PostMapping("save-event")
    public String saveEvent(@ModelAttribute("event") EventDTO eventDTO)
    {

        System.out.println("ffffffffff "+"xxxxxxxx");
        // This line is important, so we can get the latest event id, without it, we would just get id=0 because the db is responsible for the id numbers
        EventDTO savedEventDTO = eventService.createEvent(eventDTO);
        long eventID = savedEventDTO.getEventID();

        // Extracting the TicketTier objects from the eventDTO and saving them using TicketTierService, since an event could have multiple ticket tiers
        for (TicketTier tier : eventDTO.getTicketTiers()) {
            TicketTierDTO tierDTO = modelMapper.map(tier,TicketTierDTO.class);
            tierDTO.setEventID(eventID); // storing event id in ticket tier as a foreign key
            tierService.createTicketTier(tierDTO);
        }
        return "redirect:/events/getevents";
    }

    // Deleting an event
    @GetMapping("delete")
    public String deleteEvent(@RequestParam Long eventID, Principal principal, Model model)
    {
        // Principal only stores the user email, so we get user id by creating user object and extracting the id later, it's not smart but i couldn't get sessions to work
        User user = userRepository.findByEmail(principal.getName());
        EventDTO eventDTO = eventService.getEventById(eventID);
        if (eventDTO.getUserId() != user.getId()) // meaning if the event isn't yours, you can't delete it !
        {
            String errorMsg = "You can't delete other people's events, not cool buckaroo ";
            model.addAttribute("error",errorMsg);
            return "womp-womp";
        }

        // deleting the given event id
        eventService.deleteEvent(eventID);
        return "redirect:/events/getevents"; // Redirect to list page after deletion
    }


    // Editing an event
    @GetMapping("/edit")
    public String editEvent(@RequestParam Long eventID, Model model)
    {
        EventDTO eventDTO = eventService.getEventById(eventID);
        model.addAttribute("event", eventDTO);
        return "edit-event";
    }


    @PostMapping("/update-event")
    public String updateEvent(@ModelAttribute EventDTO eventDTO) {

        // Update the event using the eventService
        eventService.updateEvent(eventDTO);

        // Redirect to the event list page or a success page
        return "redirect:/events/getevents";
    }


}
