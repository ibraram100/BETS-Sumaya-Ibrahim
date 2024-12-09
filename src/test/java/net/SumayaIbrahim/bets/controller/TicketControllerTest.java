package net.SumayaIbrahim.bets.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.entity.Event;
import net.SumayaIbrahim.bets.entity.Ticket;
import net.SumayaIbrahim.bets.entity.TicketTier;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.TicketRepository;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.TicketService;
import net.SumayaIbrahim.bets.service.TicketTierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketTierService ticketTierService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // System should redirect to the appropriate web page with the appropriate message when a ticket can't be found
    @Test
    public void testRefundTicket_TicketNotFound() {
        // making an empty ticket
        when(ticketService.getTicketById(anyLong())).thenReturn(Optional.empty());
        // Passing non-existing ticket id to the ticketController
        String viewName = ticketController.refundTicket(1L, principal, model);
        // Making sure the ticket controller returns "womp-womp"
        assertEquals("womp-womp", viewName);
        // Making sure that model has the attribute errorMsg equals "Ticket not found"
        verify(model).addAttribute(eq("error"), eq("Ticket not found"));
    }

    // This test makes sure that no user can refund other user's tickets
    // meaning if you bought the ticket you are the only one who can refund it
    @Test
    public void testRefundTicket_UserNotOwner() {
        Ticket ticket = new Ticket();
        User ticketUser = User.builder().id(2L).build(); // Different user ID
        ticket.setUser(ticketUser); // Set the owner of the ticket
        User currentUser = User.builder().id(1L).build(); // Build user
        when(ticketService.getTicketById(anyLong())).thenReturn(Optional.of(ticket));
        when(userRepository.findByEmail(anyString())).thenReturn(currentUser);
        when(principal.getName()).thenReturn("user@example.com");

        String viewName = ticketController.refundTicket(1L, principal, model);

        assertEquals("womp-womp", viewName);
        verify(model).addAttribute(eq("error"), eq("You can't refund someone else's tickets !"));
    }

    @Test
    public void testRefundTicket_Success() {

        Ticket ticket = new Ticket();

        TicketTier ticketTier = new TicketTier();
        ticketTier.setTicketTierID(1L);

        ticket.setTicketTier(ticketTier);

        Date eventStartDate = new Date(System.currentTimeMillis() + 48 * 60 * 60 * 1000); // 48 hours from now which is 2 days,
        // anything lower will be considered one day, since the date format doesn't contain hours and minutes

        Event event = new Event();
        event.builder().eventID(1L).build();
        event.setEventDate(eventStartDate);
        ticket.setEvent(event);

        User ticketUser = User.builder().id(1L).build();
        ticket.setUser(ticketUser);

        TicketTierDTO ticketTierDTO = new TicketTierDTO();
        ticketTierDTO.setTicketTierID(1L);
        ticketTierDTO.setAvailableTickets(1);
        User currentUser = User.builder().id(1L).build();
        when(ticketService.getTicketById(anyLong())).thenReturn(Optional.of(ticket));
        when(userRepository.findByEmail(anyString())).thenReturn(currentUser);
        when(ticketTierService.getTierById(anyLong())).thenReturn(ticketTierDTO);
        when(principal.getName()).thenReturn("user@example.com");
        String viewName = ticketController.refundTicket(1L, principal, model);
        assertEquals("redirect:/tickets/my-tickets", viewName);
        verify(ticketTierService).updateTier(ticketTierDTO);
        verify(ticketRepository).save(ticket);
        verify(ticketService).deleteTicketById(1L);
    }

    // Test if there's at least 24 hours before the start of the event
    @Test
    public void testRefundTicket_TooLate()
    {
        Ticket ticket = new Ticket();

        Date eventStartDate = new Date(System.currentTimeMillis() + 23 * 60 * 60 * 1000); // 23 hours from now
        Event event = new Event();
        event.builder().eventID(1L).eventDate(eventStartDate).build();
        event.setEventDate(eventStartDate);

        TicketTier ticketTier = new TicketTier();
        ticketTier.setTicketTierID(1L);
        ticketTier.setEvent(event);

        ticket.setTicketTier(ticketTier);

        User ticketUser = User.builder().id(1L).build();
        ticket.setUser(ticketUser);
        ticket.setEvent(event);

        TicketTierDTO ticketTierDTO = new TicketTierDTO();
        ticketTierDTO.setTicketTierID(1L);
        ticketTierDTO.setAvailableTickets(1);
        ticketTierDTO.setEventID(event.getEventID());

        User currentUser = User.builder().id(1L).build();

        when(ticketService.getTicketById(anyLong())).thenReturn(Optional.of(ticket));
        when(userRepository.findByEmail(anyString())).thenReturn(currentUser);
        when(ticketTierService.getTierById(anyLong())).thenReturn(ticketTierDTO);
        when(principal.getName()).thenReturn("user@example.com");
        String viewName = ticketController.refundTicket(1L, principal, model);
        assertEquals("womp-womp", viewName);
        verify(model).addAttribute(eq("error"),
                eq("Tickets can only be refunded more than 24 hours before the event start date!"));
        verify(ticketRepository, never()).delete(ticket);

    }


}
