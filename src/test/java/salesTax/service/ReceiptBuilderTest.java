package salesTax.service;

import org.junit.jupiter.api.Test;
import salesTax.model.BasketItem;
import salesTax.model.TaxRatePct;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiptBuilderTest {

    @Test
    public void testSalesTaxes(){
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();

        BasketItem basketItem = new BasketItem(1, BigDecimal.TEN.setScale(2), TaxRatePct.GENERAL);
        basketItem.setProduct("product");
        basketItem.setGrossPrice(BigDecimal.valueOf(11d).setScale(2));
        basketItem.setTaxSales(BigDecimal.ONE.setScale(2));

        String receipt = receiptBuilder.createReceipt(List.of(basketItem, basketItem, basketItem));
        assertTrue(receipt.contains("1 product: 11.00"));
        assertTrue(receipt.contains("Sales Taxes: 3.00"));
        assertTrue(receipt.contains("Total: 33.00"));

    }

}