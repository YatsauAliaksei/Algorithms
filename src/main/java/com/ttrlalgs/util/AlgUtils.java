package com.ttrlalgs.util;

import com.google.common.base.Preconditions;

public abstract class AlgUtils {

    /**
     * None a recursion version.
     * NOTE: Anyway as it supports so far values no large than 20 StackOverflowError is impossible
     * Could be changed in future to return {@link java.math.BigInteger}.
     */
    public static long factorial(int n) {
        Preconditions.checkArgument(n >= 0 && n <= 20,
                "Can't be negative or large than 20. 20 is the largest factorial result which could be hold by long type.");

        long result = 1;
        while (n > 0)
            result *= n--;

        return result;
    }

    /**
     * Fibonacci numbers. None recursion version.
     * Anyway limit is not in stack overflow but in long type.
     */
    public static long fibonacci(int n) {
        Preconditions.checkArgument(n <= 93, "Max value for long type");
        if (n <= 1)
            return n;

        long result = 1;
        long previous1 = 0;
        long previous2 = 1;
        while (n-- != 2) { // first 2 fibonacci numbers ommitted
            long value = previous1 + previous2;
            result += value;
            previous1 = previous2;
            previous2 = value;
        }

        return result;
    }

    /**
     * From (n/k). Binomial coefficient. Take k from n.
     * @param numberOfPicks    - k
     * @param totalUnitsNumber - n
     * @return - number of possible variants for unique n.
     */
    public static long numberOfVariantUnique(int numberOfPicks, int totalUnitsNumber) {
        Preconditions.checkArgument(numberOfPicks <= totalUnitsNumber && numberOfPicks > 0, "Number of picks can't be bigger that total");

        return factorial(totalUnitsNumber) / (factorial(numberOfPicks) * factorial(totalUnitsNumber - numberOfPicks));
    }

    /**
     * From (n/k). (n + k - 1) / k => n/k. MultiSet. Any value in multiSet could be picked multiple times.
     * @param numberOfPicks    - k
     * @param totalUnitsNumber - n
     * @return - number of possible variants for not unique n.
     */
    public static long numberOfVariantNotUnique(int numberOfPicks, int totalUnitsNumber) {
        Preconditions.checkArgument(numberOfPicks <= totalUnitsNumber && numberOfPicks > 0, "Number of picks can't be bigger that total");

        int n = totalUnitsNumber + numberOfPicks - 1;
        return factorial(n) / (factorial(numberOfPicks) * factorial(n - numberOfPicks));
    }

    /**
     * Calculates Number of possible permutations.
     */
    public static long permutation(int number) {
        int[] numbers = new int[10];
        int numberLength = 0;
        while (number != 0) {
            int i = number % 10;
            numbers[i] += 1;
            number /= 10;
            numberLength++;
        }

        long result = factorial(numberLength);
        for (int i : numbers) {
            result /= factorial(i);
        }

        return result;
    }


}
