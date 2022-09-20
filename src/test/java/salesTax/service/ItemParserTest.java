package salesTax.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemParserTest {

    private final ItemParser itemParser = new ItemParser();;

    @Test
    public void testEmptyAndNullInput(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("");
        assertNull(parsedItem);
        ItemParser.ParsedItem parsedItem2 = itemParser.parseString(null);
        assertNull(parsedItem2);
    }

    @Test
    public void testInputStartsWithZero(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("0 choco at 1.00");
        assertNull(parsedItem);
        ItemParser.ParsedItem parsedItem2 = itemParser.parseString("01 choco at 1.00");
        assertNull(parsedItem2);
    }

    @Test
    public void testInputStartsWithOneDigit(){
         ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 1.00");
         assertEquals(1, parsedItem.getAmount());
         assertFalse(parsedItem.isImported());
         assertEquals(0, BigDecimal.ONE.compareTo(parsedItem.getUnitPrice()));
         assertEquals("choco", parsedItem.getProductInfo());
    }

    @Test
    public void testInputStartsWithOneDigitImported(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 imported choco at 1.00");
        assertEquals(1, parsedItem.getAmount());
        assertTrue(parsedItem.isImported());
    }

    @Test
    public void testFailOnStartsNegative(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("-1 choco at 1.00");
        assertNull(parsedItem);
    }

    @Test
    public void testInputStartsWithTwoDigits(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("12 choco at 1.00");
        assertEquals(12, parsedItem.getAmount());
    }

    @Test
    public void testInputStartsWithThreeDigits(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("123 choco at 1.00");
        assertEquals(123, parsedItem.getAmount());
    }

    @Test
    public void testFailOnStartsWithTooBig(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1234 choco at 1.00");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnEndsWithMissingZeros(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 1");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnEndsWithMissingAZero(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 1.0");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnEndsWithNonDigit(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 1.");
        assertNull(parsedItem);
        ItemParser.ParsedItem parsedItem2 = itemParser.parseString("1 choco at 1.00 ");
        assertNull(parsedItem2);
    }

    @Test
    public void testInputEndsWithTwoDigits(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 12.25");
        assertFalse(parsedItem.isImported());
        assertEquals(1, parsedItem.getAmount());
        assertEquals(0, BigDecimal.valueOf(12.25d).compareTo(parsedItem.getUnitPrice()));
        assertEquals("choco", parsedItem.getProductInfo());
    }

    @Test
    public void testInputEndsWithThreeDigits(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 123.00");
        assertFalse(parsedItem.isImported());
        assertEquals(0, BigDecimal.valueOf(123d).compareTo(parsedItem.getUnitPrice()));
    }

    @Test
    public void testInputFailOnEndsTooBig(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 12345.00");
        assertNull(parsedItem);
    }

    @Test
    public void testInputFailOnEndsTooLong(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 choco at 1.001");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnMissingInnerPart(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 at 1.00");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnWhiteSpaceInInnerPart(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1   at 1.00");
        assertNull(parsedItem);
    }

    @Test
    public void testFailOnInnerDigit(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 one 1 at 1.00");
        assertNull(parsedItem);
    }

    @Test
    public void testLongerInput(){
        ItemParser.ParsedItem parsedItem = itemParser.parseString("1 imported bottle of perfume at 10.00");
        assertTrue(parsedItem.isImported());
        assertEquals(1, parsedItem.getAmount());
        assertEquals(0, BigDecimal.TEN.compareTo(parsedItem.getUnitPrice()));
        assertEquals("imported bottle of perfume", parsedItem.getProductInfo());
    }
}