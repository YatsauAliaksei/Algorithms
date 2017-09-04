package com.ttrlalgs.algorithm;

public class SortUtils {

    /**
     * Swaps elements in {@param array}.
     * @param c - index of element to swap
     * @param n - index of element to swap
     */
    public static void swap(Object[] array, int c, int n) {
        Object tmp = array[c];
        array[c] = array[n];
        array[n] = tmp;
    }

    /**
     * Shifts array to the right from element {@param from}.
     * @param from - included.
     */
    public static void shiftRight(Object[] array, int from) {
        System.arraycopy(array, from, array, from + 1, array.length - from - 1);
    }

    public static void shiftRight(Object[] array, int from, int to) {
        System.arraycopy(array, from, array, from + 1, to - from);
    }
}
