package salesTax;

import salesTax.model.category.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ExampleCategoryProvider implements CategoryProvider {

    private final List<GoodsCategory> categoryList = new ArrayList<>();

    public ExampleCategoryProvider() {
        FoodCategory food = new FoodCategory(Set.of("chocolate","chocolates"));
        BookCategory book = new BookCategory(Set.of("book"));
        MedicalCategory medical = new MedicalCategory(Set.of("headache"));
        categoryList.add(food);
        categoryList.add(book);
        categoryList.add(medical);
    }

    @Override
    public List<GoodsCategory> getCategories() {
        return Collections.unmodifiableList(categoryList);
    }
}
