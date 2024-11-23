package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;

import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.WaitingListDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.UserService;
import net.SumayaIbrahim.bets.service.WaitingListService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;


@AllArgsConstructor
@Controller
@RequestMapping("/waiting-lists")
public class WaitingListController {

    private ModelMapper modelMapper;
    private WaitingListService waitingListService;
    private UserService userService;
    private EventService eventService;


    @GetMapping("join")
    public String joinWaitingList(@RequestParam("eventId") long eventId, Model model, Principal principal)
    {
        // First check if there's an actual waiting list for the event
        if (waitingListService.findWaitingListByEventId(eventId) == null)// if no waiting list was found, we create a new waiting list
        {
            WaitingList waitingList = new WaitingList();
            EventDTO eventDTO = eventService.getEventById(eventId);
            Event event = modelMapper.map(eventDTO, Event.class);
            waitingList.setEvent(event);
            waitingList.setWaitingListName(event.getEventName()+" Waiting List");
            long userId = userService.findUserByEmail(principal.getName()).getId();
            if (waitingList.getUsers() == null)
            {
                waitingList.setUsers(new ArrayList<>());
            }
            User user = userService.findUserByEmail(principal.getName());
            waitingList.getUsers().add(user);
            WaitingListDTO waitingListDTO = modelMapper.map(waitingList, WaitingListDTO.class);
            waitingListService.createWaitingList(waitingListDTO);
            return "redirect:/index";
        }
        else {
            WaitingList waitingList = waitingListService.findWaitingListByEventId(eventId);
            if (waitingList.getUsers() == null)
            {
                waitingList.setUsers(new ArrayList<>());
            }
            waitingList.getUsers().add(userService.findUserByEmail(principal.getName()));
            WaitingListDTO waitingListDTO = modelMapper.map(waitingList, WaitingListDTO.class);
            // This line is important, since ModelMapper can't convert a list of users objects, to list of long userIds
            waitingListDTO.setUserIds(waitingList.getUsers().stream().map(User::getId).toList());
            waitingListService.updateWaitingList(waitingListDTO);

            return "redirect:/index";
        }
    }

    // Leaving a waiting list
    public String leaveWaitingList(@RequestParam long eventId, Model model, Principal principal)
    {
        return null;
    }

}
