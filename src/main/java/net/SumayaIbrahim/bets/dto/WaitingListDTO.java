package net.SumayaIbrahim.bets.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WaitingListDTO {

    private long id;
    private String waitingListName;
    private long eventId;
    private List<Long> userIds;

}
