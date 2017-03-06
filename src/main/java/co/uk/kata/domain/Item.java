package co.uk.kata.domain;

import co.uk.kata.rule.ItemPriceRule;

import java.util.List;

public class Item {

    private String sku;
    private List<ItemPriceRule> itemRuleList;

    public Item(String sku, List<ItemPriceRule> itemRuleList) {
        this.sku = sku;
        this.itemRuleList = itemRuleList;
    }

    public String getSku() {
        return sku;
    }

    public List<ItemPriceRule> getItemRuleList() {
        return itemRuleList;
    }
}
