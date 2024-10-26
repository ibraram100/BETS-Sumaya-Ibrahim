package net.SumayaIbrahim.bets.mapper;

import net.SumayaIbrahim.bets.dto.UserDTO;
import net.SumayaIbrahim.bets.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getPassword()
        );
    }

    public static User mapToUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getDateOfBirth(),
                userDTO.getPassword()
        );
    }
}
