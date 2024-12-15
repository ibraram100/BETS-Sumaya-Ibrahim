package net.SumayaIbrahim.bets.service;

public class DiscountTeacher implements DiscountsFactory{
    @Override
    public double calculateDiscount(double ticketPrice){
        return ticketPrice*0.10;
    }

}
