package net.SumayaIbrahim.bets.controller;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.TicketRepository;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.*;
import org.hibernate.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private TicketService ticketService1;

    private UserRepository userRepository;
    private EventService eventService;
    private ModelMapper modelMapper;
    private TicketTierService ticketTierService;
    private EntityManager entityManager;
    private TicketRepository ticketRepository;
    private NotificationService notificationService;


    @GetMapping("all-tickets")
    public String viewAllTickets(Model model) {
        System.out.println("kkkk");
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("tickets", allTickets);
        return "all-tickets";
    }

    @GetMapping("my-tickets")
    public String viewMyTickets(Model model, Principal principal)
    {
        User user = userRepository.findByEmail(principal.getName());
        Long userId = user.getId();
        List<Ticket> allTickets = ticketService.getTicketByUserId(userId);
        model.addAttribute("tickets", allTickets);
        return "all-tickets";
    }



    @GetMapping("refund")
    @Transactional
    public String refundTicket(@RequestParam Long ticketID, Principal principal, Model model)
    {

        // There's a huge drama about some hibernate bug, some consider it a bug, and some say this is how hibernate works, and it's kind of dumb
        // Basically I have to delete the tickets parent, in this case it's the user
        // You can read more about it here https://github.com/spring-projects/spring-data-jpa/issues/1100
        // I spent a LOT of time trying to figure this out

        // Creating ticketDTO object
        Optional<Ticket> ticketDTO = ticketService.getTicketById(ticketID);
        // Creating user object
        User user = userRepository.findByEmail(principal.getName());
        // Checking if ticket actually exist
        if(ticketDTO.isPresent())
        {
            Ticket ticket = ticketDTO.get();
            // Checking if the user who actually owns the ticket have made the refund request
            if(user.getId()!= ticket.getUser().getId())
            {
                String errorMsg = "You can't refund someone else's tickets !";
                model.addAttribute("errorMsg", errorMsg);
                return "womp-womp";
            }

            // Check if the current date is at least 24 hours before the event start date
            LocalDate now = LocalDate.now();
            Date eventStartDate = ticket.getEvent().getEventDate();
            LocalDate eventStartLocalDate = eventStartDate.toLocalDate();
            long daysUntilEvent = ChronoUnit.DAYS.between(now, eventStartLocalDate);
            if (daysUntilEvent <= 1)
            {
                String errorMsg = "Tickets can only be refunded more than 24 hours before the event start date!";
                model.addAttribute("errorMsg", errorMsg); return "womp-womp";
            }


            Long tierId = ticket.getTicketTier().getTicketTierID();
            System.out.println(tierId);
            TicketTierDTO ticketTierDTO = ticketTierService.getTierById(ticket.getTicketTier().getTicketTierID());
            ticketTierDTO.setAvailableTickets(ticketTierDTO.getAvailableTickets() + 1);
            // Updating the number of available tickets
            ticketTierService.updateTier(ticketTierDTO);
            // removing the user from the ticket (it won't work without this)
            ticket.setUser(null);
            ticketRepository.save(ticket);
            // Deleting the ticket
            ticketService.deleteTicketById(ticketID);
            // Sending a message to all of the people in the waiting list when a ticket becomes available
//            EventDTO eventDTO = eventService.getEventById(ticketTierDTO.getEventID());
//            if (eventDTO.getTickets().size() >0)
//            {
//                Long waitingListId = eventDTO.getWaitingList().getId();
//                String msg = "There's avialable tickets at "+ eventDTO.getEventName()+" Hurry and buy tickets now !";
//                notificationService.sendToUsersInWaitingList(waitingListId,msg);
//            }


            return "redirect:/tickets/my-tickets";
        }
        else
        {
            String errorMsg = "Ticket not found";
            model.addAttribute("errorMsg", errorMsg);
            return "womp-womp";
        }

    }

}