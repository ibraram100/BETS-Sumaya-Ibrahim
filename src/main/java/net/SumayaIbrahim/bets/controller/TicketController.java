package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.TicketService;
import net.SumayaIbrahim.bets.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
@AllArgsConstructor
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private UserRepository userRepository;

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
    public String refundTicket(@RequestParam Long ticketID)
    {
        System.out.println("kkkffffffffffk");
        return null;
    }

}