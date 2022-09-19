package salesTax;

import java.util.Set;

public class FoodCategory extends GoodsCategory {

    public FoodCategory(Set<String> items) {
        super(TaxRatePct.NO_TAX, items);
    }
}
