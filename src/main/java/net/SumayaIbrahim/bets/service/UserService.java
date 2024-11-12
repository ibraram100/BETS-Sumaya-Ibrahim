package net.SumayaIbrahim.bets.service;
import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;

import java.util.List;
import java.util.Optional;

// Just contains the interfaces for creating Users
public interface UserService {

     UserDTO createUser (UserDTO userDTO);
     Optional<User> findUserById(Long userID);

     User findUserByEmail(String email);
     UserDTO updateUser(UserDTO userDTO);

     List<User> findAllUsers();
     void deleteUser(Long userID);
}
