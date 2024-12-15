package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.EventReviewDTO;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.EventReview;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.EventReviewService;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/reviews")
public class EventReviewController {
    @Autowired
    EventReviewService eventReviewService;
    @Autowired
    EventService eventService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserService userService;

    @PostMapping("/post-review")
    public String createReview(@ModelAttribute EventReviewDTO eventReviewDTO,
                               @RequestParam Long eventID, Model model, Principal principal)
    {
        EventDTO eventDTO = eventService.getEventById(eventID);
        if (eventDTO == null)
        {
            model.addAttribute("womp-womp","event not found !");
            return "womp-womp";
        }
        EventReview eventReview = modelMapper.map(eventReviewDTO,EventReview.class);
        eventDTO.addReview(eventReview);
        Event event = modelMapper.map(eventDTO,Event.class);

        User user = userService.findUserByEmail(principal.getName());
        eventReview.setEvent(event);
        eventReview.setUserId(user.getId());
        eventReviewDTO.setEvent(event);
//        eventReviewService.createReview(eventReviewDTO);
//        EventReview eventReview = modelMapper.map(eventReviewDTO,EventReview.class);
        eventService.updateEvent(eventDTO);
        return "/index";
//        return reviewService.createReview(eventReview);
    }


}
