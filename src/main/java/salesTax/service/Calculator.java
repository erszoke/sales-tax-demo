package salesTax.service;

import salesTax.model.BasketItem;
import salesTax.model.TaxRatePct;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private static final int SCALE = 2;
    private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private final static BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100L).setScale(SCALE);
    // 5/100 reciprocal 100/5=20
    private final static BigDecimal TWENTY = BigDecimal.valueOf(20);

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
        BigDecimal tax = unitPrice
                .multiply(BigDecimal.valueOf(taxRatePct)).setScale(2, ROUNDING_MODE)
                .divide(ONE_HUNDRED, ROUNDING_MODE);

        //rounding up for nearest 0.05
        return tax.multiply(TWENTY).setScale(0, RoundingMode.UP)
                .divide(TWENTY, 2, ROUNDING_MODE);
    }
}
