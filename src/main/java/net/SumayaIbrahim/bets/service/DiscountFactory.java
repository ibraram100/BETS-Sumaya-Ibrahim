package net.SumayaIbrahim.bets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//public interface DiscountsFactory {
//    DiscountsDTO chooseDiscount(DiscountsDTO discountsDTO);
//    double calculateDiscount(double ticketPrice);
//}
@Component
public class DiscountFactory {
    private final Map<String, DiscountStrategy> strategies;

    @Autowired
    public DiscountFactory(List<DiscountStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getSimpleName().toLowerCase(),
                        strategy -> strategy
                ));
    }

    public DiscountStrategy chooseDiscount(String discountType) {
        return strategies.getOrDefault(discountType.toLowerCase(), ticketPrice -> ticketPrice); // No discount by default
    }
}
