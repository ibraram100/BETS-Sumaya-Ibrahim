package net.SumayaIbrahim.bets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_reviews")
public class EventReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;
    @Column(nullable = false)
    private int stars;
    private String comment;
    @Column(name = "user_id")
    private long userId; // the user who left the comment

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
