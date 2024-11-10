package net.SumayaIbrahim.bets.dto;





import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.User;


import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO
{
    private Long id;
    private String name;
    private List<User> users;
}
