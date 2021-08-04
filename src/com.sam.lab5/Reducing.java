package com.sam.lab5;

import com.sam.lab.Dish;

import static com.sam.lab.Dish.menu;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;


public class Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);


        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));

        // with no initial value it returns optional
        Optional<Integer> mas = numbers.stream().reduce( (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> max1 = numbers.stream().reduce(Integer::max);
        max1.ifPresent(System.out::println);


        int min = numbers.stream().reduce(0,(x,y)->x<y?x:y);
        System.out.println("min with lambda  = " + min);
        // is equal to
        Optional<Integer> minOptional  = numbers.stream().reduce((x,y)->x<y?x:y);
        System.out.println("min with lambda  = " + min);
        // doesn't take initial value it returns optional
        Optional<Integer> minOpt = numbers.stream().reduce(Integer::min);
        minOpt.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }

}
