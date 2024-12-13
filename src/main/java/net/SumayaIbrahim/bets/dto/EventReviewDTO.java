package net.SumayaIbrahim.bets.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.SumayaIbrahim.bets.entity.Event;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventReviewDTO {
    private long id;
    private int stars;
    private String comment;
    private long userId; // the user who left the comment
    private Event event;
}

