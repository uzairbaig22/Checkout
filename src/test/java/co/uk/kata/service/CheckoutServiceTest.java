package co.uk.kata.service;

import co.uk.kata.domain.Item;
import co.uk.kata.domain.SpecialPrice;
import co.uk.kata.exception.KataCheckoutException;
import co.uk.kata.rule.ItemPriceRule;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    private Item itemA;
    private Item itemB;
    private Item itemC;
    private Item itemD;
    private List<Item> basket;

    @Before
    public void setup() {
        checkoutService = new CheckoutService();
    }

    @Test
    public void shouldReturnTotalAmoutOfBasket() {
        itemA = new Item("A", Arrays.asList(new ItemPriceRule(0.50, new SpecialPrice(3, 1.30))));
        itemB = new Item("B", Arrays.asList(new ItemPriceRule(0.30, new SpecialPrice(2, 0.45))));
        itemC = new Item("C", Arrays.asList(new ItemPriceRule(0.20, null)));
        itemD = new Item("D", Arrays.asList(new ItemPriceRule(0.15, null)));

        basket = Arrays.asList(itemC, itemD);
        final Double totalAmout = checkoutService.processCheckout(basket);

        assertThat(0.35, is(totalAmout));
    }

    @Test
    public void shouldReturnTotalAmoutOfItemIfMoreThanOneAreAppearInDifferentOrder() {

        itemA = new Item("A", Arrays.asList(new ItemPriceRule(0.50, new SpecialPrice(3, 1.30))));
        itemB = new Item("B", Arrays.asList(new ItemPriceRule(0.30, new SpecialPrice(2, 0.45))));
        itemC = new Item("C", Arrays.asList(new ItemPriceRule(0.20, null)));

        basket = Arrays.asList(itemB, itemA, itemB, itemC);
        final Double totalAmout = checkoutService.processCheckout(basket);
        assertThat(1.15, is(totalAmout));
    }

    @Test
    public void shouldReturnTotalAmoutOfBasketWhenThereAreMultipleAProducts() {
        itemA = new Item("A", Arrays.asList(new ItemPriceRule(0.50, new SpecialPrice(3, 1.30))));
        itemC = new Item("C", Arrays.asList(new ItemPriceRule(0.20, null)));

        basket = Arrays.asList(itemA, itemA, itemC, itemA);
        final Double totalAmout = checkoutService.processCheckout(basket);

        assertThat(1.50, is(totalAmout));
    }

    @Test
    public void shouldReturnTotalAmoutIfNoRuleApply() {
        itemA = new Item("A", Arrays.asList(new ItemPriceRule(0.50, null)));
        itemC = new Item("C", Arrays.asList(new ItemPriceRule(0.20, null)));

        basket = Arrays.asList(itemA, itemC, itemC);
        final Double totalAmout = checkoutService.processCheckout(basket);

        assertThat(0.90, is(totalAmout));
    }

    @Test
    public void shouldReturnTotalAmoutIfMoreThanOneRuleApplyOnSameProduct() {
        itemA = new Item("A", Arrays.asList(new ItemPriceRule(0.50, new SpecialPrice(3, 1.30))));
        itemC = new Item("C", Arrays.asList(new ItemPriceRule(0.20, null)));

        basket = Arrays.asList(itemA, itemA, itemC, itemA, itemA, itemA, itemA, itemA);
        final Double totalAmout = checkoutService.processCheckout(basket);

        assertThat(3.30, is(totalAmout));
    }

    @Test(expected = KataCheckoutException.class)
    public void shouldThrowExceptionWhenBasketIsEmpty() {
       basket = Arrays.asList();
       checkoutService.processCheckout(basket);
    }
}