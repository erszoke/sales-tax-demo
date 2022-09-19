package salesTax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemParserTest {

    private ItemParser itemParser = new ItemParser();;

    @Test
    public void testEmptyAndNullInput(){
        assertFalse(itemParser.matches(""));
        assertFalse(itemParser.matches(null));
    }

    @Test
    public void testInputStartsWithZero(){
        assertFalse(itemParser.matches("0 choco at 1.00"));
        assertFalse(itemParser.matches("01 choco at 1.00"));
    }

    @Test
    public void testInputStartsWithOneDigit(){
        assertTrue(itemParser.matches("1 choco at 1.00"));
    }

    @Test
    public void testFailOnStartsNegative(){
        assertFalse(itemParser.matches("-1 choco at 1.00"));
    }

    @Test
    public void testInputStartsWithTwoDigits(){
        assertTrue(itemParser.matches("12 choco at 1.00"));
    }

    @Test
    public void testInputStartsWithThreeDigits(){
        assertTrue(itemParser.matches("123 choco at 1.00"));
    }

    @Test
    public void testFailOnStartsWithTooBig(){
        assertFalse(itemParser.matches("1234 choco at 1.00"));
    }

    @Test
    public void testFailOnEndsWithMissingZeros(){
        assertFalse(itemParser.matches("1 choco at 1"));
    }

    @Test
    public void testFailOnEndsWithMissingAZero(){
        assertFalse(itemParser.matches("1 choco at 1.0"));
    }

    @Test
    public void testFailOnEndsWithNonDigit(){
        assertFalse(itemParser.matches("1 choco at 1."));
        assertFalse(itemParser.matches("1 choco at 1.00 "));
    }

    @Test
    public void testInputEndsWithTwoDigits(){
        assertTrue(itemParser.matches("1 choco at 12.00"));
    }

    @Test
    public void testInputEndsWithThreeDigits(){
        assertTrue(itemParser.matches("1 choco at 123.00"));
    }

    @Test
    public void testInputFailOnEndsTooBig(){
        assertFalse(itemParser.matches("1 choco at 12345.00"));
    }

    @Test
    public void testInputFailOnEndsTooLong(){
        assertFalse(itemParser.matches("1 choco at 1.001"));
    }

    @Test
    public void testFailOnMissingInnerPart(){
        assertFalse(itemParser.matches("1 at 1.00"));
    }

    @Test
    public void testFailOnWhiteSpacetInnerPart(){
        assertFalse(itemParser.matches("1   at 1.00"));
    }

    @Test
    public void testFailOnInnerDigit(){
        assertFalse(itemParser.matches("1 one 1 at 1.00"));
    }

    @Test
    public void testLongInput(){
        assertTrue(itemParser.matches("1 imported bottle of perfume at 10.00"));
    }
}