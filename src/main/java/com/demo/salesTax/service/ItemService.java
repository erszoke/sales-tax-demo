package com.demo.salesTax.service;

import com.demo.salesTax.model.BasketItem;
import com.demo.salesTax.model.TaxRatePct;
import com.demo.salesTax.model.category.CategoryProvider;
import com.demo.salesTax.model.category.GoodsCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemService {

    private final Calculator calculator = new Calculator();
    private final CategoryProvider categoryProvider;

    public ItemService(CategoryProvider categoryProvider){
        this.categoryProvider = categoryProvider;
    }

    public BasketItem createBasketItem(ItemParser.ParsedItem parsedItem){

        TaxRatePct taxRatePct = getTaxRatePct(parsedItem.getProductInfo());

        BasketItem item = new BasketItem(parsedItem.getAmount(), parsedItem.getUnitPrice(), taxRatePct);
        item.setProduct(parsedItem.getProductInfo());
        if(parsedItem.isImported()){
            item.setAdditionalTaxRate(TaxRatePct.IMPORTED);
        }
        calculateTaxAndGrossPrice(item);
        return item;
    }

    private TaxRatePct getTaxRatePct(String productInfo) {
        String[] words = productInfo.split(" ");
        TaxRatePct taxRatePct = null;
        for(String productWord: words){
            if(productWord.length() > 2) {//exclude of, on etc.
                taxRatePct = findCategoryTaxRate(productWord);
                if (taxRatePct != null) {
                    break;
                }
            }
        }
        return Objects.requireNonNullElse(taxRatePct, TaxRatePct.GENERAL);
    }

    private TaxRatePct findCategoryTaxRate(String productWord) {
        for(GoodsCategory category: categoryProvider.getCategories()){
            if(category.contains(productWord)){
                return category.getTaxRatePct();
            }
        }
        return null;
    }

    protected void calculateTaxAndGrossPrice(BasketItem item){
        BigDecimal tax = calculator.calcTaxRate(item);
        item.setTaxSales(tax);
        item.setGrossPrice(item.getUnitNetPrice().add(item.getTaxSales()));
    }
}
