package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;

import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.WaitingListDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.NotificationService;
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
    private NotificationService notificationService;


    @GetMapping("join")
    public String joinWaitingList(@RequestParam("eventId") long eventId, Model model, Principal principal)
    {

        // Checking if the event exists
        if (eventService.getEventById(eventId) == null) {
            String errorMsg = "Event was not found!";
            model.addAttribute("error", errorMsg);
            return "womp-womp";
        }
        User user = userService.findUserByEmail(principal.getName());
        long userId = user.getId();
        EventDTO eventDTO = eventService.getEventById(eventId);
        Event event = modelMapper.map(eventDTO, Event.class);

        // First check if there's an actual waiting list for the event
        if (waitingListService.findWaitingListByEventId(eventId) == null)// if no waiting list was found, we create a new waiting list
        {
            WaitingList waitingList = new WaitingList();

            waitingList.setEvent(event);
            waitingList.setWaitingListName(event.getEventName()+" Waiting List");
            if (waitingList.getUsers() == null)
            {
                waitingList.setUsers(new ArrayList<>());
            }
            waitingList.getUsers().add(user);
            WaitingListDTO waitingListDTO = modelMapper.map(waitingList, WaitingListDTO.class);
            waitingListService.createWaitingList(waitingListDTO);
            String msg = "You have joined the waiting list for "+event.getEventName()+" event, you will be notified when tickets are available.";
            notificationService.createNotification(userId,msg);
            return "redirect:/index";
        }
        else {
            WaitingList waitingList = waitingListService.findWaitingListByEventId(eventId);
            if (waitingList.getUsers() == null)
            {
                waitingList.setUsers(new ArrayList<>());
            }
            // Making sure user is not already joined in the waiting list
            if (waitingList.getUsers().contains(user))
            {
                String errorMsg = "You are already joined in the waiting list!";
                model.addAttribute("error", errorMsg);
                return "womp-womp";
            }
            waitingList.getUsers().add(userService.findUserByEmail(principal.getName()));
            WaitingListDTO waitingListDTO = modelMapper.map(waitingList, WaitingListDTO.class);
            // This line is important, since ModelMapper can't convert a list of users objects, to list of long userIds
            waitingListDTO.setUserIds(waitingList.getUsers().stream().map(User::getId).toList());

            String msg = "You have joined the waiting list for "+event.getEventName()+" event, you will be notified when tickets are available.";
            notificationService.createNotification(userId,msg);
            waitingListService.updateWaitingList(waitingListDTO);
            return "redirect:/index";
        }
    }

    // Leaving a waiting list
    public String leaveWaitingList(@RequestParam long eventId, Model model, Principal principal)
    {
        return null;
    }

    // Sending notifications

    public String notifyUsers()
    {
        return null;
    }

}
