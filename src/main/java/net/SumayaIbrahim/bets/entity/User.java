// 2024/10/26
// creating the User class, this is the first class to be implemented in this project
package net.SumayaIbrahim.bets.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

// The setter and getter annotation allows us to use set and get methods without
// actually writing it in the class, it just makes life a lot easier
@Setter
@Getter
// The AllArgsConstructor makes a constructor method that should take all arguments to make an object
// The NoArgsConstructor makes a constructor method that takes no argument to make an object
@AllArgsConstructor
@NoArgsConstructor
// The @Entity annotation in Spring Boot is used to mark a class as a JPA entity, which means it represents a table in a relational database
@Entity
@Table(name = "users")
public class User {
    @Id // this specifies id as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generate the id by incremting the last id in the database
    private long id;
    @Column(unique = true, nullable = false) // it's like saying the email shouldn't be null and should be unique
    private String email;
    @Column(name = "first_name") // it would be named "first_name" in the db
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String password;


}
// viola !!!, after running this code you should find "users" table in your db
