package net.SumayaIbrahim.bets.service.UserImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.repository.UserRepository;
import net.SumayaIbrahim.bets.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

// needs more comments or something
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        // Basically saving the user to db
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
        return savedUserDTO;
    }
}
