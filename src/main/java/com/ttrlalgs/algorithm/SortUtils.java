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
     * Shifts array to the right from element {@param index}.
     * @param index - included.
     */
    public static void shiftRight(Object[] array, int index) {
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
    }
}
