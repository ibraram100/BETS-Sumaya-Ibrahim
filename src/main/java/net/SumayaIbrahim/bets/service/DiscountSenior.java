/package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.DiscountsDTO;
import org.springframework.stereotype.Component;

//public class DiscountSenior implements DiscountsFactory {
//    @Override
//    public DiscountsDTO chooseDiscount(DiscountsDTO discountsDTO) {
//        return null;
//    }
//
//    public double calculateDiscount(double ticketPrice) {
//        return ticketPrice * 0.05;
//    }
//}



@Component
public class DiscountSenior implements DiscountStrategy {
    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * 0.95; // 5% discount for seniors
    }
}


