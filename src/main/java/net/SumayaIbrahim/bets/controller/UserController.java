// This class is intended to for dealing with users, such as deleting, editing their
// info or their roles, only admins can do these operations, admins shouldn't be able
// to edit or delete other admins information, because admins are basically "made men" and are untouchable
// There's basically 3 roles in this system, admins, Event Organizers and attendees


package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller // This class is able to handle http requests
@RequestMapping("users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    // building create User REST api
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Displaying all users
    @GetMapping("/all-users")
    public String allUsers(Model model)
    {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam Long userID, Model model)
    {
        Optional<User> user = userService.findUserById(userID);
        if (user.isPresent()) // If user is found
        {
            User tempUser = user.orElse(null);
            UserDTO userDTO = modelMapper.map(tempUser,UserDTO.class);
            model.addAttribute("user",userDTO);
            return "index";
        }
        else
        {
            // I should be returning an error page saying user doesn't exist or something
            return null;
        }

    }


}
