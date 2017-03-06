package co.uk.kata.domain;

public class SpecialPrice {

    private Integer quantity;

    private Double price;

    public SpecialPrice(Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isRuleApply(final Integer basketItemQuantity) {
        return basketItemQuantity >= quantity;
    }
}
