package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.dto.DiscountsDTO;
import org.springframework.stereotype.Component;

//public class DiscountStudent implements DiscountsFactory {
//    @Override
//    public DiscountsDTO chooseDiscount(DiscountsDTO discountsDTO) {
//        return null;
//    }
//
//    public double calculateDiscount(double ticketPrice) {
//        return ticketPrice*0.10;
//    }
//
//}

@Component
public class DiscountStudent implements DiscountStrategy {
    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * 0.9; // 10% discount for students
    }
}

