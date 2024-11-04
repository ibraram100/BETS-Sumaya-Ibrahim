package net.SumayaIbrahim.bets.service.UserImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
        return savedUserDTO;
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public void saveUser(UserDTO userDTO) {

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }
}
