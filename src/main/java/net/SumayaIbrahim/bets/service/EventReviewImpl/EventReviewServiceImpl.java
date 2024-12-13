package net.SumayaIbrahim.bets.service.EventReviewImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventReviewDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.EventReview;
import net.SumayaIbrahim.bets.repository.EventReviewRepository;
import net.SumayaIbrahim.bets.service.EventReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor

public class EventReviewServiceImpl implements EventReviewService {

    EventReviewRepository eventReviewRepository;
    private ModelMapper modelMapper;
    @Override
    public EventReviewDTO createReview(EventReviewDTO eventReviewDTO) {
        EventReview eventReview = modelMapper.map(eventReviewDTO, EventReview.class);
        eventReviewRepository.save(eventReview);
        Event event = eventReviewDTO.getEvent();
        event.setEventReviews(Collections.singletonList(eventReview));
        return null;
    }
}
