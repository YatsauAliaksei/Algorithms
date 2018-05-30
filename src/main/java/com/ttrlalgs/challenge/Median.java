package com.ttrlalgs.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Median {

    public static void main(String[] args) {
        int[] a = {3, 7, 1, 9, 15, 2};
        List<Integer> sorted = new ArrayList<>();
        for (int a_i = 0; a_i < a.length; a_i++) {
            int k = a[a_i];
            int i = Collections.binarySearch(sorted, k);
            sorted.add(Math.abs(i + 1), k);
            double median;
            if (sorted.size() % 2 == 0) {
                int middle = sorted.size() / 2;
                median = (sorted.get(middle - 1) + sorted.get(middle)) / 2.0;
            } else {
                median = sorted.get(sorted.size() / 2);
            }
            System.out.println(median);
        }
    }
}
