package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.TicketTierDTO;

public interface TicketTierService {
    TicketTierDTO createTicketTier(TicketTierDTO ticketTierDTO);
    TicketTierDTO getTierById(Long tierID);
    TicketTierDTO updateTier(TicketTierDTO tierDTO);
    public void flush();
}
