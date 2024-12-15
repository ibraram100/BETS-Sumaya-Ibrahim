package net.SumayaIbrahim.bets.service;

public class DiscountSenior implements DiscountsFactory {
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * 0.05;
    }
}