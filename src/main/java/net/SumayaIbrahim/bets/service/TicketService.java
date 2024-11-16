package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.entity.Ticket;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicketById(Long ticketID);
    List<Ticket> getAllTickets();
    List<Ticket>getTicketByUserId(Long userId);

}
