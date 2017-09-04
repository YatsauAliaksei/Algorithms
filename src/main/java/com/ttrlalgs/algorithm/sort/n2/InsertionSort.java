package com.ttrlalgs.algorithm.sort.n2;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.AbsSort;

public class InsertionSort extends AbsSort {

    @SuppressWarnings("unchecked")
    protected <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        if (check(collection))
            return collection;

        Object[] arr = new Object[collection.size()];

        for (T element : collection) {
            for (int k = 0; k < arr.length; k++) {
                Object v = arr[k];
                if (v == null) {
                    arr[k] = element;
                    break;
                }

                if (isGreater.test(element, (T) v))
                    continue;

                SortUtils.shiftRight(arr, k);
                arr[k] = element;
                break;
            }
        }
        return (Collection<T>) Arrays.asList(arr);
    }
}
