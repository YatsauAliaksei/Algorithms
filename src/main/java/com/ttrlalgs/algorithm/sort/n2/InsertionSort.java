package com.ttrlalgs.algorithm.sort.n2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiPredicate;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.Sort;

public class InsertionSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        return sort(collection, (T o1, T o2) -> comparator.compare(o1, o2) > 0);
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        return sort(collection, (T o1, T o2) -> o1.compareTo(o2) > 0);
    }

    @SuppressWarnings("unchecked")
    private <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
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
