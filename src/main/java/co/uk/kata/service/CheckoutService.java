package co.uk.kata.service;

import co.uk.kata.domain.Item;
import co.uk.kata.exception.KataCheckoutException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.DoubleAdder;

public class CheckoutService {

    private final DecimalFormat df = new DecimalFormat("#.00");
    private Map<Item, Integer> itemQuantityMap;

    public CheckoutService() {
        itemQuantityMap = new HashMap<>();
    }

    public Double processCheckout(final List<Item> basket) {

        if (basket == null || basket.size() == 0) {
            throw new KataCheckoutException();
        }

        basket.stream().forEach(item -> {
            if (itemQuantityMap.get(item) != null) {
                itemQuantityMap.put(item, itemQuantityMap.get(item) + 1);
            } else {
                itemQuantityMap.put(item, 1);
            }
        });
        return scanItemAndApplyRule(itemQuantityMap);
    }

    private Double scanItemAndApplyRule(final Map<Item, Integer> itemQuantityMap) {

        final DoubleAdder totalPrice = new DoubleAdder();
        itemQuantityMap.forEach((item, quantity) -> {
            final double totalPriceOfItem = item.getItemRuleList().stream().mapToDouble(itemRule -> itemRule.execute(quantity)).sum();
            totalPrice.add(totalPriceOfItem);
        });

        return Double.valueOf(df.format(totalPrice.doubleValue()));
    }
}
