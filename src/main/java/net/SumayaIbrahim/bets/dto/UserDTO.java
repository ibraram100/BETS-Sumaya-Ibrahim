package net.SumayaIbrahim.bets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

// This will be used later so we can convert this object to something the db can understand (or at least that's what i'm guessing)
public class UserDTO {
    private long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String password;

}
