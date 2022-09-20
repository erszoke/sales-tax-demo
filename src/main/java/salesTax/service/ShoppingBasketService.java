package salesTax.service;

import salesTax.model.BasketItem;
import salesTax.model.category.CategoryProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingBasketService {

    private final Logger LOGGER = Logger.getLogger("ShoppingBasketService");

    private final ItemParser itemParser = new ItemParser();
    private final ItemService itemService;
    private final ReceiptBuilder receiptBuilder = new ReceiptBuilder();

    public ShoppingBasketService(CategoryProvider categoryProvider) {
        itemService = new ItemService(categoryProvider);
    }

    public String createReceipt(List<String> itemStrings){
        List<BasketItem> items = new ArrayList<>();
        try {
            itemStrings.forEach(itemString -> items.add(readItem(itemString)));
            return receiptBuilder.createReceipt(items);

        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Failed to create receipt", e);
        }
        return null;
    }

    private BasketItem readItem(String itemData){
        ItemParser.ParsedItem parsedItem = itemParser.parseString(itemData);
        if(parsedItem == null){
            throw new IllegalArgumentException("Couldn't parse input: " + itemData);
        }
        return itemService.createBasketItem(parsedItem);
    }
}
