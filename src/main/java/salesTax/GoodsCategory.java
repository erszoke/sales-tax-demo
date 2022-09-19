package salesTax;

import java.util.HashSet;
import java.util.Set;

public abstract class GoodsCategory {

    private final TaxRatePct taxRatePct;
    private final Set<String> members = new HashSet<>();

    public GoodsCategory(TaxRatePct taxRatePct, Set<String> members) {
        this.taxRatePct = taxRatePct;
        this.members.addAll(members);
    }

    public boolean contains(String item){
        return item != null && members.contains(item);
    }

    public TaxRatePct getTaxRatePct() {
        return taxRatePct;
    }
}
