package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
// Controller for registering Event Organizers
public class RegisterController {
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EventOrganizerDTO eventOrgDTO)
    {
        String response = authService.register(eventOrgDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
