package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.TicketTier;

public interface TicketTierService {
    TicketTierDTO createTicketTier(TicketTierDTO ticketTierDTO);
    TicketTierDTO getTierById(Long tierID);
    TicketTierDTO updateTier(TicketTierDTO tierDTO);

}
