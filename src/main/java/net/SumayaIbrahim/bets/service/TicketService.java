package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.entity.Ticket;

import java.util.List;
import java.util.Optional;



public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    Optional<Ticket> getTicketById(Long ticketID);
    List<Ticket> getAllTickets();
    List<Ticket>getTicketByUserId(Long userId);
    void deleteTicketById(Long ticketID);
    public double calculatePrice(double ticketPrice, String discountType);
    public void flush();

}
