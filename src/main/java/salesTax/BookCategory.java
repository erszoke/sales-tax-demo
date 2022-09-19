package salesTax;

import java.util.Set;

public class BookCategory extends GoodsCategory {

    public BookCategory(Set<String> items) {
        super(TaxRatePct.NO_TAX, items);
    }
}
