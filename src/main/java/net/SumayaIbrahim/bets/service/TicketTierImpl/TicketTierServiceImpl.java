package net.SumayaIbrahim.bets.service.TicketTierImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.repository.TicketTierRepository;
import net.SumayaIbrahim.bets.service.TicketTierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketTierServiceImpl implements TicketTierService {
    private TicketTierRepository tierRepository;
    private ModelMapper modelMapper;
    @Override
    public TicketTierDTO createTicketTier(TicketTierDTO ticketTierDTO) {
        TicketTier ticketTier = modelMapper.map(ticketTierDTO,TicketTier.class);
        TicketTier savedTier = tierRepository.save(ticketTier);
        TicketTierDTO savedTierDTO = modelMapper.map(savedTier,TicketTierDTO.class);
        return  savedTierDTO;

    }
}
