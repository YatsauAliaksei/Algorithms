package com.ttrlalgs.algorithm;

public class SortUtils {

    public static void swap(Object[] array, int c, int n) {
        Object tmp = array[c];
        array[c] = array[n];
        array[n] = tmp;
    }

    public static void shiftRight(Object[] array, int index) {
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
    }
}
