package com.demo.salesTax.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private static final Pattern PATTERN = Pattern.compile("([1-9]\\d{0,2})( )([a-zA-Z]+[a-zA-Z ]*)( at )(\\d{1,4}.\\d{2})");
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_PRODUCT = 3;
    private static final int INDEX_OF_UNIT_PRICE = 5;

    private static final String  IMPORTED = "imported";
    public ParsedItem parseString(String itemString){
        if(itemString != null && !itemString.isEmpty()) {
            Matcher matcher = PATTERN.matcher(itemString);
            if (matcher.matches()) {
                return createParsedItem(matcher);
            }
        }
        return null;
    }

    private ParsedItem createParsedItem(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group(INDEX_OF_AMOUNT));
        String product = matcher.group(INDEX_OF_PRODUCT);
        BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(matcher.group(INDEX_OF_UNIT_PRICE))).setScale(2, RoundingMode.HALF_UP);

        ParsedItem parsedItem = new ParsedItem();
        if (product.contains(IMPORTED)) {
            parsedItem.isImported = true;
            if(!product.startsWith(IMPORTED)){
                String prefix = IMPORTED + " ";
                product = prefix + product.replace(prefix, "");
            }
        }
        parsedItem.productInfo = product;
        parsedItem.unitPrice = unitPrice;
        parsedItem.amount = amount;
        return parsedItem;
    }
    public static class ParsedItem {

        private ParsedItem(){}

        private String productInfo;
        private boolean isImported;
        private int amount;
        private BigDecimal unitPrice;

        public String getProductInfo() {
            return productInfo;
        }

        public boolean isImported() {
            return isImported;
        }

        public int getAmount() {
            return amount;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }
    }
}
