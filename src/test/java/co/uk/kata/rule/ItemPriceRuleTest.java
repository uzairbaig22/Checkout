package co.uk.kata.rule;

import co.uk.kata.domain.SpecialPrice;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemPriceRuleTest {

    private ItemPriceRule itemPriceRule;

    @Test
    public void applyNoRuleWhenQuantityIsLessThenSpeicalPrice() {
        itemPriceRule = new ItemPriceRule(.45, new SpecialPrice(2, .60));
        assertThat(.45, is(itemPriceRule.execute(1)));
    }

    @Test
    public void applyRuleWhenQuantityIsEqualToSpecialPrice() {
        itemPriceRule = new ItemPriceRule(.45, new SpecialPrice(2, .60));
        assertThat(.60, is(itemPriceRule.execute(2)));
    }

    @Test
    public void applyRuleWhenQuantityIsGreaterThanSpecialPrice() {
        itemPriceRule = new ItemPriceRule(.45, new SpecialPrice(2, .60));
        assertThat(1.05, is(itemPriceRule.execute(3)));
    }

    @Test
    public void applyRuleTwiceWhenQuantityIsApplicableForSpecialPriceMoreThanOnce() {
        itemPriceRule = new ItemPriceRule(.45, new SpecialPrice(2, .60));
        assertThat(1.65, is(itemPriceRule.execute(5)));
    }

    @Test
    public void simplyMultiplyItemPriceIfThereIsNoRule() {
        itemPriceRule = new ItemPriceRule(.45, null);
        assertThat(1.35, is(itemPriceRule.execute(3)));
    }

}