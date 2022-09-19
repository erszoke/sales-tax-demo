package salesTax;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemServiceTest {

    private final ItemService itemService = new ItemService();

    @Test
    public void calcForTenGeneralTax(){
        BasketItem item = new BasketItem(1, BigDecimal.TEN, TaxRatePct.GENERAL, null);
        itemService.calculateTaxAndGrossPrice(item);

        assertNotNull(item.getGrossPrice());
        assertEquals(0, BigDecimal.valueOf(11).compareTo(item.getGrossPrice()));
    }

    @Test
    public void calcForTenImportedTax(){
        BasketItem item = new BasketItem(1, BigDecimal.TEN, TaxRatePct.NO_TAX, TaxRatePct.IMPORTED);
        itemService.calculateTaxAndGrossPrice(item);

        assertNotNull(item.getGrossPrice());
        assertEquals(0, BigDecimal.valueOf(10.5d).compareTo(item.getGrossPrice()));
    }

    @Test
    public void calcForTenGeneralAndImportedTax(){
        BasketItem item = new BasketItem(1, BigDecimal.TEN, TaxRatePct.GENERAL, TaxRatePct.IMPORTED);
        itemService.calculateTaxAndGrossPrice(item);

        assertNotNull(item.getGrossPrice());
        assertEquals(0, BigDecimal.valueOf(11.5).compareTo(item.getGrossPrice()));
    }

}