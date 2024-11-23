// This Class is made to view the list of available events and allows attendees to buy tickets.
// This class doesn't contain any admin web views
package net.SumayaIbrahim.bets.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.TicketService;
import net.SumayaIbrahim.bets.service.TicketTierService;
import net.SumayaIbrahim.bets.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller // This class is able to handle http requests
@RequestMapping("/view")
public class ViewController {
    private EventService eventService;
    private TicketTierService tierService;
    private TicketService ticketService;
    private UserService userService;


    // Displays a list of all of the available tickets
    @GetMapping("/get-events")
    public String getAllEvents(Model model)
    {
        List<EventDTO> events = eventService.GetAllEvents();
        model.addAttribute("events", events);
        return "all-events"; // it will go to events.html in resources folder, it will also send events to that file
    }

    @GetMapping("/view-event")
    public String viewEvent(Model model, EventDTO eventDTO)
    {
        EventDTO event = eventService.getEventById(eventDTO.getEventID());
        // Checking if we have enough tickets, if not, then the buy-ticket2 template will automatically display
        // a join to waiting list button
        boolean availableTickets =  false;
        int sum = 0;
        // Calculating the number of available tickets
        for (TicketTier tier :event.getTicketTiers())
        {
            sum = sum + tier.getAvailableTickets();
        }
        if (sum != 0)
        {
            availableTickets = true;
        }

        model.addAttribute("availableTickets", availableTickets);
        model.addAttribute("event",event);

        return "buy-ticket2";
    }

    // Buying tickets
    @PostMapping("/buy-ticket")
    public String buyTicket(@RequestParam Long eventID, HttpServletRequest request,
                            @RequestParam Integer numTickets,
                            Principal principal,
                            Model model)
    {
        System.out.println(request.getParameter("ticketTier"));
        Long tierID = Long.valueOf(request.getParameter("ticketTier"));
        TicketTierDTO tierDTO = tierService.getTierById(tierID);
        EventDTO eventDTO = eventService.getEventById(tierDTO.getEventID());
        User user = userService.findUserByEmail(principal.getName());

        // if the number of tickets that the user asked for is greater than the available tickets, show an error
        if (numTickets > tierDTO.getAvailableTickets())
        {
            String errorMsg = "There's no enough tickets !";
            model.addAttribute("error", errorMsg);
            return "womp-womp";
        }
        else
        {
            for(int i=0;i<numTickets;i++)
            {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setUserId(user.getId());
                ticketDTO.setSold(true);
                ticketDTO.setTicketTierId(tierID);
                ticketDTO.setEventId(eventDTO.getEventID());
                ticketService.createTicket(ticketDTO);
            }
            tierDTO.setAvailableTickets(tierDTO.getAvailableTickets()- numTickets);
            tierService.updateTier(tierDTO);
        }
        List<EventDTO> events = eventService.GetAllEvents();
        model.addAttribute("events", events);
        return "all-events";
    }



}
