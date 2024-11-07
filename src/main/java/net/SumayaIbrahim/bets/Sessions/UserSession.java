package net.SumayaIbrahim.bets.Sessions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSession {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;

    }
}