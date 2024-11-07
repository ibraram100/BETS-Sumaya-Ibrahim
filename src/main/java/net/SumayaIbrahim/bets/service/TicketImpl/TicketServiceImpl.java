package net.SumayaIbrahim.bets.service.TicketImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.repository.TicketRepository;
import net.SumayaIbrahim.bets.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private ModelMapper modelMapper;
    private TicketRepository ticketRepository;
    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = modelMapper.map(ticketDTO,Ticket.class);
        Ticket savedTicket = ticketRepository.save(ticket);
        TicketDTO savedTicketDTO = modelMapper.map(savedTicket, TicketDTO.class);
        savedTicketDTO.setId(savedTicket.getId());

        return savedTicketDTO;
    }

    @Override
    public TicketDTO getTicketById(Long ticketID) {
        return null;
    }
}
