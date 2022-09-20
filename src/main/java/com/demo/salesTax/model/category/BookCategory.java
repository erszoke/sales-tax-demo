package com.demo.salesTax.model.category;

import com.demo.salesTax.model.TaxRatePct;

import java.util.Set;

public class BookCategory extends GoodsCategory {

    public BookCategory(Set<String> items) {
        super(TaxRatePct.NO_TAX, items);
    }
}
