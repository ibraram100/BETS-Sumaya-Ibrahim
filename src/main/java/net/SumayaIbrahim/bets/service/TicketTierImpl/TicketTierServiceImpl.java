package net.SumayaIbrahim.bets.service.TicketTierImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.repository.TicketTierRepository;
import net.SumayaIbrahim.bets.service.TicketTierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public TicketTierDTO getTierById(Long tierID) {
        Optional<TicketTier> optionalTier = tierRepository.findById(tierID); // we used optional because it might not return a thing

        TicketTier tier = optionalTier.orElse(null);
        TicketTierDTO tierDTO = modelMapper.map(tier,TicketTierDTO.class);
        return tierDTO;

    }

    @Override
    public TicketTierDTO updateTier(TicketTierDTO tierDTO) {
        Optional<TicketTier> optionalTicketTier = tierRepository.findById(tierDTO.getTicketTierID());
        if (optionalTicketTier.isPresent()) {
            // Update the existing tier with the new data
            TicketTier existingTicketTier = optionalTicketTier.get();
            existingTicketTier = modelMapper.map(tierDTO, TicketTier.class);
            tierRepository.save(existingTicketTier);
        }
        else
        {
            // Handle the case where the tier is not found
            return null;
        }
        return null;
    }
}
