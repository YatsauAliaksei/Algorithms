package com.ttrlalgs.algorithm.number;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class NumberUtils {

    public static List<Long> findFactors(long number) {
        List<Long> accumulator = new ArrayList<>();  // what initial size should be?

        findFactors(number, 2L, accumulator);
        return accumulator;
    }

    private static void findFactors(long number, long delimiter, List<Long> accumulator) {
        LongStream.rangeClosed(delimiter, Math.abs(number))
                .filter(l -> number % l == 0)
                .findFirst()
                .ifPresent(value -> {
                    accumulator.add(value);
                    findFactors(number / value, value, accumulator);
                });
    }
}
