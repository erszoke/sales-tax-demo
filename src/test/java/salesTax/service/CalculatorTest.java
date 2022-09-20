package salesTax.service;

import org.junit.jupiter.api.Test;
import salesTax.model.BasketItem;
import salesTax.model.TaxRatePct;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void calcBasketItemNoTax() {
        BasketItem item = new BasketItem(1, BigDecimal.valueOf(12.49d), TaxRatePct.NO_TAX);
        BigDecimal taxRate = calculator
                .calcTaxRate(item);

        assertEquals(0, BigDecimal.ZERO.compareTo(taxRate));
    }

    @Test
    void calcBasketItemGeneralTax() {
        BasketItem item = new BasketItem(1, BigDecimal.valueOf(12.49d), TaxRatePct.GENERAL);
        BigDecimal taxRate = calculator.calcTaxRate(item);

        assertEquals(0, BigDecimal.valueOf(1.25d).compareTo(taxRate));
    }

    @Test
    void calcBasketItemNoTaxAndAdditionalTax1() {
        BasketItem item = new BasketItem(1, BigDecimal.valueOf(10d), TaxRatePct.NO_TAX);
        item.setAdditionalTaxRate(TaxRatePct.IMPORTED);

        BigDecimal taxRate = calculator.calcTaxRate(item);

        assertEquals(0, BigDecimal.valueOf(0.5d).compareTo(taxRate));
    }

    @Test
    void calcTaxRounding() {
        BigDecimal taxRate = calculator
                .calcSalesTax( TaxRatePct.IMPORTED.getTaxRatePct(), BigDecimal.valueOf(11.25d));

        assertEquals(0, BigDecimal.valueOf(0.6d).compareTo(taxRate));
    }

    @Test
    void calcBasketItemNoTaxAndAdditionalTax3() {
        BasketItem item = new BasketItem(1, BigDecimal.valueOf(47.50), TaxRatePct.GENERAL);
        item.setAdditionalTaxRate(TaxRatePct.IMPORTED);

        BigDecimal taxRate = calculator.calcTaxRate(item);

        assertEquals(0, BigDecimal.valueOf(7.2d).compareTo(taxRate));
    }

    @Test
    void calcBasketItemGeneralAndAdditionalTax() {
        BasketItem item = new BasketItem(1, BigDecimal.valueOf(27.99d), TaxRatePct.GENERAL);
        item.setAdditionalTaxRate(TaxRatePct.IMPORTED);

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