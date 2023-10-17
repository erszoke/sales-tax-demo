# Readme

### Run the project
- Make sure to be in the root directory
- Clean and build the project, run the command:
```
mvn package
```
This will also generate a jar file with all the dependencies which we will run after.
- Run the `Main` method in `Main.java` by running
```
mvn exec:java
```
- Alternatively, you can run the `main` method in `Main.java` in your chosen IDE, e.g. `IntelliJ`

## Functionality
This application demonstrates making a receipt for a shopping basket with a cash register.
- categorizes items in the basket
- calculates tax and gross prices for individual items
- gives the total of taxes and gross prices
- prints out the receipt details of the predefined test data set

### Assumptions and rules

- all category members are as usual as the test input
- all category members can be described with one word -> e.g. from "headache pills" "headache" or "pills" is enough or use both separetaly
- first match wins if a product matches more than one category
- if a product may occur both in singular and plural form then both form must be presented in that category
- 1 < n < 100 where n: amount of products
- 1.00 < n < 1000.00 where n: net unit price of a product
- when a product is in the basket more than one times then each occurrence handled separately


### Additional info
`ExampleCategoryProvider` class demonstrates the given test category members. For real production, products could come
from files or by consuming an API. 
