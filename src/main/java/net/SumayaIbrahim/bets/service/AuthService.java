package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.EventOrganizerDTO;
import net.SumayaIbrahim.bets.dto.LoginDTO;


public interface AuthService {
    String register(EventOrganizerDTO eventOrganizerDTO);
    String login(LoginDTO loginDTO);
}
