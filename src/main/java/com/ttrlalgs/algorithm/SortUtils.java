package com.ttrlalgs.algorithm;

public class SortUtils {

    public static  <T> void swap(T[] array, int c, int n) {
        T tmp = array[c];
        array[c] = array[n];
        array[n] = tmp;
    }
}
