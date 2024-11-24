package net.SumayaIbrahim.bets.service.UserImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.Role;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.RoleRepository;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// needs more comments or something
@Service
@AllArgsConstructor
//@NoArgsConstructor // it took me an embarrassing amount of time to fix this
@Setter
@Getter
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        // Basically saving the user to db
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Storing an encrypted password, since we don't want to store them in plain text
        // By default, users are granted the Attendee role
        Role role = roleRepository.findByName("ROLE_ADMIN"); // Checking if the role actually exists
        if(role == null){
            role = checkRoleExist("ROLE_ADMIN");
        }
        user.setRoles(Arrays.asList(role));
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
        return savedUserDTO;
    }




    // Basically it creates a new role
    private Role checkRoleExist(String roleName){
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }


    @Override
    public Optional<User> findUserById(Long userID) {
        return userRepository.findById(userID);

    }

    @Override
    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if (userDTO != null)
        {
            // We do this to encrypt the password
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User user = modelMapper.map(userDTO,User.class);

            userRepository.save(user);
        }
       return null;
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(Long userID)
    {
        userRepository.deleteById(userID);
    }

}
