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
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.TicketService;
import net.SumayaIbrahim.bets.service.TicketTierService;
import net.SumayaIbrahim.bets.service.UserService;
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

    @GetMapping("deleteTicket")
    public void deleteTicket(Long ticketId)
    {
        Optional<Ticket> ticket = ticketService1.getTicketById(ticketId);
        Ticket ticket1 = ticket.get();
        ticket1.getEvent().getTickets().remove(ticket1);
        ticket1.getUser().getTickets().remove(ticket1);
        ticketService.deleteTicketById(ticketId);

    }

    @GetMapping("refund")
    @Transactional
    public String refundTicket(@RequestParam Long ticketID, Principal principal)
    {

        // There's a huge drama about some hibernate bug, some consider it a bug, and some say this is how hibernate works, and it's kind of dumb
        // basically i should delete the ticket first, and then do the rest or otherwise the delete won't work for some reason
        // You can read more about it here https://github.com/spring-projects/spring-data-jpa/issues/1100
        // I spent a LOT of time trying to figure this out

        assert ticketID != null; // making sure ticketID is not null
        // Creating ticketDTO object
        Optional<Ticket> ticketDTO = ticketService.getTicketById(ticketID);
        // Converting optional to ticket




        if(ticketDTO.isPresent())
        {

            Ticket ticket = ticketDTO.get();
            TicketTierDTO ticketTierDTO = ticketTierService.getTierById(ticket.getTicketTier().getTicketTierID());
            ticketTierDTO.setAvailableTickets(ticketTierDTO.getAvailableTickets() + 1);
            // Updating the number of available tickets
            ticketTierService.updateTier(ticketTierDTO);
            // Deleting the ticket
            ticketDTO = null;
            ticket = null;
            ticketService.flush();
            ticketService.deleteTicketById(ticketID);

            return "redirect:/tickets/my-tickets";
        }
        else
        {
            return "womp-womp";
        }

    }

}