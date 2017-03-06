package co.uk.kata.rule;

import co.uk.kata.domain.SpecialPrice;

public class ItemPriceRule {

    private Double unitPrice;

    private SpecialPrice specialPrice;

    public ItemPriceRule(Double unitPrice,  SpecialPrice specialPrice) {
        this.unitPrice = unitPrice;
        this.specialPrice = specialPrice;
    }

    public Double execute(Integer basketQuantity) {

        if (specialPrice != null && specialPrice.isRuleApply(basketQuantity)) {
            final int numberOfItemsWithoutSpecialPrice =  basketQuantity % specialPrice.getQuantity();
            final int numberOfTimesRulesApply = basketQuantity / specialPrice.getQuantity();

            return specialPrice.getPrice() * numberOfTimesRulesApply + numberOfItemsWithoutSpecialPrice * unitPrice;
        }
         return basketQuantity * unitPrice;
    }
}
