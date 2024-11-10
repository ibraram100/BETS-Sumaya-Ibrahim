// This class is intended to for dealing with users, such as deleting, editing their
// info or their roles, only admins can do these operations, admins shouldn't be able
// to edit or delete other admins information, because admins are basically "made men" and are untouchable
// There's basically 3 roles in this system, admins, User Organizers and attendees


package net.SumayaIbrahim.bets.controller;


import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.Role;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.RoleService;
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
    private RoleService roleService;
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



//    @GetMapping("/edit-user")
//    public String editUser(@RequestParam Long userID, Model model)
//    {
//        System.out.println("TEST");
//
////        UserDTO userDTO = userService.getUserById(userID);
////        model.addAttribute("user", userDTO);
////        return "edit-user";
//        return null;
//    }
    
    
    @GetMapping("/edit-user")
    public String editUser(@RequestParam Long userID, Model model)
    {
        System.out.println("hhhh");
        Optional<User> user = userService.findUserById(userID);
        List<Role> availableRoles = roleService.getAllRoles();
        if (user.isPresent()) // If user is found
        {
            User tempUser = user.orElse(null);
            UserDTO userDTO = modelMapper.map(tempUser,UserDTO.class);
            model.addAttribute("user",userDTO);
            model.addAttribute("availableRoles",availableRoles);
            return "edit-user";
        }
        else
        {
            // I should be returning an error page saying user doesn't exist or something
            return null;
        }

    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserDTO userDTO)
    {
        // For some reason the userID is 0 when i recive the object
        userService.updateUser(userDTO);
        return "redirect:/users/all-users";
    }


}
