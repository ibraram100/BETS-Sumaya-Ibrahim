package net.SumayaIbrahim.bets.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.Sessions.UserSession;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.service.EventService;
import net.SumayaIbrahim.bets.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@AllArgsConstructor
@SessionAttributes({"user"})
@Controller
public class AuthController {
    private UserService userService;
    private EventService eventService;
    private UserSession userSession;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register") // Takes users to the registration form
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model)
    {

        User existingUser = userService.findUserByEmail(userDTO.getEmail());
//      Checking if email already exists
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }


        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.createUser(userDTO);
        return "redirect:/register?success";
    }
    // Handling login
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    // Displaying all users
//    @GetMapping("/users")
//    public String allUsers(Model model)
//    {
//        List<User> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }

}



