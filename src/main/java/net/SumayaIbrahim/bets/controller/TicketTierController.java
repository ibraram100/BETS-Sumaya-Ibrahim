package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.TicketTierDTO;
import net.SumayaIbrahim.bets.service.TicketTierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ticketTiers")
public class TicketTierController {
    private TicketTierService tierService;
    @PostMapping
    public ResponseEntity<TicketTierDTO> createTier(@RequestBody TicketTierDTO tierDTO){
        TicketTierDTO savedTier = tierService.createTicketTier(tierDTO);
        return new ResponseEntity<>(savedTier, HttpStatus.CREATED);

    }
}
