package com.sam.lab5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.*;
import static java.util.stream.Collectors.toList;

public class FlatMapping {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squareNumbers = numbers.stream().map(n -> n * n).collect(toList());
        out.println(squareNumbers);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        List<int[]> pairsSum3 = numbers1.stream()

                .flatMap(i ->
                        numbers2
                                .stream()
                                .filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());

        pairs.forEach(pair -> out.printf("(%d, %d)", pair[0], pair[1]));
        out.println();
        pairsSum3.forEach(pair -> out.printf("(%d, %d)", pair[0], pair[1]));


    }
}
