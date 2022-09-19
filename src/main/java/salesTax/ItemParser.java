package salesTax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private static final Pattern pattern = Pattern.compile("([1-9]\\d{0,2})( )([a-zA-Z]+[a-zA-Z ]*)( at )(\\d{1,4}.\\d{2})");
    private static final int INDEX_OF_AMOUNT = 0;
    private static final int INDEX_OF_PRODUCT = 3;
    private static final int INDEX_OF_UNIT_PRICE = 4;
    public boolean matches(String itemString){
        if(itemString == null || itemString.isEmpty()) {
            return false;
        } else {
            Matcher matcher = pattern.matcher(itemString);
            return matcher.matches();
        }
    }
}
