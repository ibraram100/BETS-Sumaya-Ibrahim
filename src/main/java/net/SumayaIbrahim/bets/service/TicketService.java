package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.TicketDTO;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicketById(Long ticketID);
}
