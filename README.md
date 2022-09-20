# Readme

### Run the project
- Make sure to be in the root directory
- Clean and build the project, run the command:
```aidl
mvn install
```
This will also generate a jar file with all the dependencies which we will run once
it has been created.
- Run the `Main` method in `Main.java` by running
```
mvn exec:java
```
- Alternatively, you can run the `main` method in `Main.java` in your chosen IDE, e.g. `IntelliJ`

## Functionality
This application demonstrates making a receipt for a shopping basket with a cash register.
- categorizes items in the basket
- calculate tax and gross price for individual items
- gives the total of taxes and gross prices

### Assumptions and rules

- all category members are usual as the test input
- all category members can be described in one word -> e.g. from "headache pills" "headache" is enough
- if a product may occur both in singular and plural form then both form must be in the category
- 1 < n < 100 where n: amount of product
- 1.00 < n < 1000.00 where n: net unit price of product
- f a product is in the basket more than one times then all occurrences handled separately


### Additional info
`ExampleCategoryProvider` class demonstrates the given test data. For real production, categories could come
from files or by consuming an API. 
