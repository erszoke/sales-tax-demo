package salesTax;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private static final int SCALE = 2;
    private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private final static BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100L).setScale(SCALE);

    public BigDecimal calcTaxRate(BasketItem basketItem){
        BigDecimal salesTax = calcSalesTax(basketItem.getTaxRate().getTaxRatePct(), basketItem.getUnitNetPrice());
        if(basketItem.getAdditionalTaxRate() != null){
            BigDecimal additionalTax = calcSalesTax(basketItem.getAdditionalTaxRate().getTaxRatePct(), basketItem.getUnitNetPrice());
            salesTax = salesTax.add(additionalTax);
        }
        if(basketItem.getAmount() > 1){
            salesTax = salesTax.multiply(BigDecimal.valueOf(basketItem.getAmount()));
        }
        return salesTax.setScale(SCALE, ROUNDING_MODE);
    }

    protected BigDecimal calcSalesTax(int taxRatePct, BigDecimal unitPrice) {
        if(TaxRatePct.NO_TAX.getTaxRatePct() == taxRatePct){
            return BigDecimal.ZERO;
        }
        return unitPrice
                .multiply(BigDecimal.valueOf(taxRatePct).setScale(SCALE))
                .divide(ONE_HUNDRED, ROUNDING_MODE);
    }
}
