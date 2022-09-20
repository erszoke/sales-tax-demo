package com.demo.salesTax;

import com.demo.salesTax.service.ShoppingBasketService;

import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String... args){

        ShoppingBasketService shoppingBasketService = new ShoppingBasketService(new ExampleCategoryProvider());

        List<List<String>> inputs = List.of(getInput1(), getInput2(), getInput3());
        for(int i=0; i<inputs.size(); i++){
            System.out.println("Output "+ (i+1) +":");
            System.out.println();
            String receipt = shoppingBasketService.createReceipt(inputs.get(i));
            System.out.println(Objects.requireNonNullElse(receipt, "Something went wrong."));
            System.out.println();
        }
    }

    private static List<String> getInput1(){
       return List.of("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85");
    }

    private static List<String> getInput2(){
        return List.of("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50");
    }

    private static List<String> getInput3(){
        return List.of("1 imported bottle of perfume at 27.99",
                "1 bottle of perfume at 18.99",
                "1 packet of headache pills at 9.75",
                "1 box of imported chocolates at 11.25");
    }
}
