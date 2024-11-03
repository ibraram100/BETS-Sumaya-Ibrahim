package net.SumayaIbrahim.bets.service.AuthImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.Exceptions.ApiException;
import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.dto.LoginDTO;
import net.SumayaIbrahim.bets.entity.EventOrganizer;
import net.SumayaIbrahim.bets.repository.EventOrganizerRepository;
import net.SumayaIbrahim.bets.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {

    private EventOrganizerRepository eventOrgRepo;
    @Autowired // Without it, it's just not gonna work
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private AuthenticationManager authManager;

    @Override
    public String register(EventOrganizerDTO eventOrganizerDTO) {
        // Checking if email already exists
        // Our db already doesn't allow duplicate emails, but we do this as a form of error handling,
        // so that the system won't crash or show weird error messages to the users
        if (eventOrgRepo.existsByEmail(eventOrganizerDTO.getEmail()))
        {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Email already in use, get another email !");
        }
        if (eventOrgRepo.existsByUsername(eventOrganizerDTO.getUsername()))
        {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Username is already in use, be more creative !");
        }
        // Can't store password as a plain text, so we have to encode it first
        eventOrganizerDTO.setPassword(passwordEncoder.encode(eventOrganizerDTO.getPassword()));
        // Converting the dto object to an entity so it can be stored in the db
        EventOrganizer savedEventOrg = modelMapper.map(eventOrganizerDTO, EventOrganizer.class);
        // Saving the object to the db
        eventOrgRepo.save(savedEventOrg);


        return "Account Created Successfully !";
    }

    @Override
    // Authenticating Users, spring boot actually does it all, we just have to give it the username and password and viola !
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()
        ));

        // We do this to ensure that user's login info is not lost, and user's won't have to
        // re-login with each request, it's kind of like cookies, except it's way cooler
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User " + " logged in successfully !";
    }
}
