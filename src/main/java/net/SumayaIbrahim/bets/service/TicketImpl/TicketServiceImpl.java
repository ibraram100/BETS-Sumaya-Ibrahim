package net.SumayaIbrahim.bets.service.TicketImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.TicketDTO;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.repository.TicketRepository;
import net.SumayaIbrahim.bets.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private ModelMapper modelMapper;
    private TicketRepository ticketRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = modelMapper.map(ticketDTO,Ticket.class);
        Ticket savedTicket = ticketRepository.save(ticket);
        TicketDTO savedTicketDTO = modelMapper.map(savedTicket, TicketDTO.class);
        savedTicketDTO.setId(savedTicket.getId());
        return savedTicketDTO;
    }

    @Override
    public Optional<Ticket> getTicketById(Long ticketID) {

        return ticketRepository.findById(ticketID);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


    @Override
    public List<Ticket> getTicketByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Override
    public void deleteTicketById(Long ticketID) {
        ticketRepository.deleteById(ticketID);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }
}
