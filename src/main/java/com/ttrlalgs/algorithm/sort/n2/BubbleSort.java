package com.ttrlalgs.algorithm.sort.n2;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.AbsSort;

public class BubbleSort extends AbsSort {

    protected <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        if (check(collection))
            return collection;

        @SuppressWarnings("unchecked")
        T[] array = (T[]) collection.toArray();

        boolean wasSwap;
        do {
            wasSwap = false;
            for (int current = 0; current < array.length - 1; current++) {
                int next = current + 1;
                if (isGreater.test(array[current], array[next])) {
                    SortUtils.swap(array, current, next);
                    wasSwap = true;
                }
            }

        } while (wasSwap);

        return Arrays.asList(array);
    }
}
