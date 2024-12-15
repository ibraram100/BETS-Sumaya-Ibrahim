package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.DiscountsDTO;

public interface DiscountsFactory {
    DiscountsDTO chooseDiscount(DiscountsDTO discountsDTO);
    double calculateDiscount(double ticketPrice);
}
