package net.SumayaIbrahim.bets.service;

public class DiscountStudent implements DiscountsFactory {
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice*0.10;
    }

}
