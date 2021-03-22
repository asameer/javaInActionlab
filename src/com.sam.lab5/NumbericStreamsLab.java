package com.sam.lab5;

import java.util.stream.IntStream;

public class NumbericStreamsLab {

    public static void main(String[] args) {
//        NumericReange
        IntStream eventNumbers = IntStream.rangeClosed(1,100).filter(n->n%2==0);
        eventNumbers.forEach(System.out::println);
//        System.out.println(eventNumbers.count());
       IntStream.range(1,100).filter(n->n%2==0).forEach(System.out::println);

    }
}
