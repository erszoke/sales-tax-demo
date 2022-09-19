package salesTax;

import java.math.BigDecimal;
import java.util.List;

public class ItemService {

    private final Calculator calculator = new Calculator();

    public void calculateTaxAndGrossPrice(BasketItem item){
        BigDecimal tax = calculator.calcTaxRate(item);
        item.setTaxSales(tax);
        item.setGrossPrice(item.getUnitNetPrice().add(item.getTaxSales()));
    }

    public TaxRatePct findTaxRateType(String[] words, List<GoodsCategory> goodsCategories){
        for(String word : words){
            for(GoodsCategory category : goodsCategories){
                if(category.contains(word)){
                    return category.getTaxRatePct();
                }
            }
        }
        return TaxRatePct.GENERAL;
    }
}
