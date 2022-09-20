package salesTax.service;

import salesTax.model.BasketItem;

import java.math.BigDecimal;
import java.util.Collection;

public class ReceiptBuilder {

    public String createReceipt(Collection<BasketItem> items){
        StringBuilder sb = new StringBuilder();

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal taxesTotal = BigDecimal.ZERO;

        for(BasketItem item : items){
            sb.append(item.getAmount())
                    .append(" ")
                    .append(item.getProduct())
                    .append(": ")
                    .append(item.getGrossPrice())
                    .append(System.lineSeparator());
            taxesTotal = taxesTotal.add(item.getTaxSales());
            total = total.add(item.getGrossPrice());
        }
        sb.append("Sales Taxes: ").append(taxesTotal).append(System.lineSeparator());
        sb.append("Total: ").append(total);
        return sb.toString();
    }
}
