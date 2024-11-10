// 2024/10/26
// creating the User class, this is the first class to be implemented in this project
package net.SumayaIbrahim.bets.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


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
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id // this specifies id as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generate the id by incrementing the last id in the database
    private long id;
    @Column(unique = true, nullable = false) // it's like saying the email shouldn't be null and should be unique
    private String email;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "first_name") // it would be named "first_name" in the db
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // event could have multiple TicketTiers
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    private Long roleID;


}
// viola !!!, after running this code you should find "users" table in your db
