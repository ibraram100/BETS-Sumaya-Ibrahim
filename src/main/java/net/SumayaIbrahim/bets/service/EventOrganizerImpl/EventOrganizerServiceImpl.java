package net.SumayaIbrahim.bets.service.EventOrganizerImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.entity.EventOrganizer;
import net.SumayaIbrahim.bets.repository.EventOrganizerRepository;
import net.SumayaIbrahim.bets.service.EventOrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
@AllArgsConstructor
public class EventOrganizerServiceImpl implements EventOrganizerService {
    private ModelMapper modelMapper;
    private EventOrganizerRepository eventOrgRepo;
    @Override
    public EventOrganizerDTO createEventOrganizer(EventOrganizerDTO eventOrgDTO) {
        EventOrganizer eventOrganizer = modelMapper.map(eventOrgDTO,EventOrganizer.class);
        EventOrganizer savedEventOrg = eventOrgRepo.save(eventOrganizer);
        EventOrganizerDTO savedEventOrgDTO = modelMapper.map(savedEventOrg,EventOrganizerDTO.class);
        return savedEventOrgDTO;
    }
}
