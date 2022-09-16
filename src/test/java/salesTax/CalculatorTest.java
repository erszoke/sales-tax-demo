package salesTax;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    private final Calculator calculator = new Calculator();


    @Test
    void calcBasketItemNoTax() {
        BasketItem item = new BasketItem(BigDecimal.valueOf(12.49d), TaxRatePct.NO_TAX, null);
        BigDecimal taxRate = calculator
                .calcTaxRate(item);

        assertEquals(0, BigDecimal.ZERO.compareTo(taxRate));
    }

    @Test
    void calcBasketItemGeneralTax() {
        BasketItem item = new BasketItem(BigDecimal.valueOf(12.49d), TaxRatePct.GENERAL, null);
        BigDecimal taxRate = calculator.calcTaxRate(item);

        assertEquals(0, BigDecimal.valueOf(1.25d).compareTo(taxRate));
    }

    @Test
    void calcBasketItemNoTaxAndAdditionalTax() {
        BasketItem item = new BasketItem(BigDecimal.valueOf(10d), TaxRatePct.NO_TAX,TaxRatePct.IMPORTED);
        BigDecimal taxRate = calculator.calcTaxRate(item);

        assertEquals(0, BigDecimal.valueOf(0.5d).compareTo(taxRate));
    }

    @Test
    void calcBasketItemGeneralAndAdditionalTax() {
        BasketItem item = new BasketItem(BigDecimal.valueOf(27.99d), TaxRatePct.GENERAL, TaxRatePct.IMPORTED);
        BigDecimal taxRate = calculator
                .calcTaxRate(item);

        assertEquals(0,  BigDecimal.valueOf(4.2d).compareTo(taxRate));
    }

    @Test
    void calcNoTax() {
        BigDecimal taxRate = calculator
                .calcSalesTax( TaxRatePct.NO_TAX.getTaxRatePct(), BigDecimal.valueOf(10.00d));

        assertEquals(0, BigDecimal.ZERO.compareTo(taxRate));
    }

    @Test
    void calcZeroNoTax() {
        BigDecimal taxRate = calculator
                .calcSalesTax( TaxRatePct.NO_TAX.getTaxRatePct(), BigDecimal.ZERO);

        assertEquals(0, BigDecimal.ZERO.compareTo(taxRate));
    }

    @Test
    void calcGeneralTax() {
        BigDecimal taxRate = calculator
                .calcSalesTax( TaxRatePct.GENERAL.getTaxRatePct(), BigDecimal.valueOf(10.00d));

        assertEquals(0, BigDecimal.ONE.compareTo(taxRate));
    }

    @Test
    void calcZeroGeneralTax() {
        BigDecimal taxRate = calculator
                .calcSalesTax( TaxRatePct.GENERAL.getTaxRatePct(), BigDecimal.ZERO);

        assertEquals(0, BigDecimal.ZERO.compareTo(taxRate));
    }
}