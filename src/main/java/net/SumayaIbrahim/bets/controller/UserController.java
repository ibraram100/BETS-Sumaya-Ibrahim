package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController // This class is able to handle http requests
@RequestMapping("/api/Users")
public class UserController {
    private UserService userService;

    // building create User REST api
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


}
