package net.SumayaIbrahim.bets.controller;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.dto.LoginDTO;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// I might need to rename this file to AuthController, since it deals with registration and login
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
// Controller for registering Event Organizers
public class RegisterController {
    private AuthService authService;

    // Register new event organizers
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EventOrganizerDTO eventOrgDTO)
    {
        String response = authService.register(eventOrgDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO)
    {
        String response = authService.login(loginDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
