package net.SumayaIbrahim.bets.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.security.Principal;
import java.util.ArrayList;

import net.SumayaIbrahim.bets.dto.EventDTO;
import net.SumayaIbrahim.bets.dto.WaitingListDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.NotificationService;
import net.SumayaIbrahim.bets.service.UserService;
import net.SumayaIbrahim.bets.service.WaitingListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class WaitingListControllerTest {

    @InjectMocks
    private WaitingListController waitingListController;

    private MockMvc mockMvc;

    @Mock
    private WaitingListService waitingListService;

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(waitingListController).build();
    }

    @Test
    public void testJoinExistingWaitingList() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventID(1L);
        eventDTO.setEventName("Test Event");

        Event event = new Event();
        event.setEventID(1L);
        event.setEventName("Test Event");

        WaitingList waitingList = new WaitingList();
        waitingList.setId(1L);
        waitingList.setEvent(event);
        waitingList.setUsers(new ArrayList<>());

        when(userService.findUserByEmail(anyString())).thenReturn(user);
        when(eventService.getEventById(anyLong())).thenReturn(eventDTO);
        when(modelMapper.map(any(EventDTO.class), eq(Event.class))).thenReturn(event);
        when(waitingListService.findWaitingListByEventId(anyLong())).thenReturn(waitingList);
        when(modelMapper.map(any(WaitingList.class), eq(WaitingListDTO.class))).thenReturn(new WaitingListDTO());

        Principal principal = () -> "test@example.com";

        mockMvc.perform(get("/waiting-lists/join")
                        .param("eventId", "1")
                        .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        verify(notificationService, times(1)).createNotification(eq(1L), anyString());
    }

    // When joining a non-existing waiting list, the system should automatically create a waiting list for the event
    @Test
    void testJoinNonExistingWaitingList() {
        long eventId = 1L;
        long userId = 1L;
        String userEmail = "user@example.com";
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventID(eventId);
        eventDTO.setEventName("Sample Event");
        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);
        Event event = new Event();

        when(userService.findUserByEmail(anyString())).thenReturn(user);
        when(eventService.getEventById(anyLong())).thenReturn(eventDTO);
        when(modelMapper.map(eventDTO, Event.class)).thenReturn(event);
        when(waitingListService.findWaitingListByEventId(anyLong())).thenReturn(null);
        when(principal.getName()).thenReturn(userEmail);

        String viewName = waitingListController.joinWaitingList(eventId, model, principal);

        assertEquals("redirect:/index", viewName);
        verify(notificationService).createNotification(eq(userId), anyString());
    }

    // The system shouldn't allow a user to join to the same waiting list twice,
    // meaning there should be no duplicates in a waiting list
    @Test
    void testJoinWaitingList_WhenUserAlreadyInList() {
        long eventId = 1L;
        long userId = 1L;
        String userEmail = "user@example.com";
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventID(eventId);
        eventDTO.setEventName("Sample Event");
        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);
        Event event = new Event();

        WaitingList existingWaitingList = new WaitingList();
        existingWaitingList.setEvent(event);
        existingWaitingList.setUsers(new ArrayList<>());
        existingWaitingList.getUsers().add(user);

        when(userService.findUserByEmail(anyString())).thenReturn(user);
        when(eventService.getEventById(anyLong())).thenReturn(eventDTO);
        when(modelMapper.map(eventDTO, Event.class)).thenReturn(event);
        when(waitingListService.findWaitingListByEventId(anyLong())).thenReturn(existingWaitingList);
        when(principal.getName()).thenReturn(userEmail);

        String viewName = waitingListController.joinWaitingList(eventId, model, principal);

        assertEquals("womp-womp", viewName);
        verify(model).addAttribute(eq("error"), eq("You are already joined in the waiting list!"));
        verify(waitingListService, never()).createWaitingList(any(WaitingListDTO.class));
        verify(notificationService, never()).createNotification(eq(userId), anyString());
    }

    // The system should display the appropriate error message when an event doesn't exist
    @Test
    void testJoinWaitingList_WhenEventDoesNotExist() {
        long eventId = 1L;
        long userId = 1L;
        String userEmail = "user@example.com";
        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);

        when(eventService.getEventById(anyLong())).thenReturn(null);

        String viewName = waitingListController.joinWaitingList(eventId, model, principal);

        assertEquals("womp-womp", viewName);
        verify(model).addAttribute(eq("error"), eq("Event was not found!"));
        verify(waitingListService, never()).createWaitingList(any(WaitingListDTO.class));
        verify(notificationService, never()).createNotification(eq(userId), anyString());
    }




}