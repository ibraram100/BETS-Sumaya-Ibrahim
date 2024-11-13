package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@AllArgsConstructor
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @GetMapping("all-tickets")
    public String viewAllTickets(Model model) {
        System.out.println("kkkk");
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("tickets", allTickets);
        return "all-tickets";
    }
}