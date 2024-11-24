package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This class is responsible for displaying notifications to the users
// Each user can have multiple notifications, while each notification can have one user
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String message;

        // We have to convert it to tinyint or else the db won't accept it, is code without silly mistakes is even considered real code ?
        @Column(name = "is_read", columnDefinition = "tinyint(1) default 0")
        private boolean read;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }


