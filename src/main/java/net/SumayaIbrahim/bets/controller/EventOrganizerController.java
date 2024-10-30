package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.service.EventOrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/eventOrganizers")
public class EventOrganizerController {
    private EventOrganizerService eventOrgService;
    @PostMapping
    public ResponseEntity<EventOrganizerDTO> createUser(@RequestBody EventOrganizerDTO eventOrgDTO)
    {
        EventOrganizerDTO savedEventOrgDTO = eventOrgService.createEventOrganizer(eventOrgDTO);
        return new ResponseEntity<>(savedEventOrgDTO, HttpStatus.CREATED);
    }

}
