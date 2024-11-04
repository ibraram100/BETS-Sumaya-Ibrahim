package net.SumayaIbrahim.bets.service;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;

import java.util.List;

// Just contains the interfaces for creating Users
public interface UserService {

     UserDTO createUser (UserDTO userDTO);
     void saveUser(UserDTO userDTO);

     User findUserByEmail(String email);

     List<UserDTO> findAllUsers();
}
