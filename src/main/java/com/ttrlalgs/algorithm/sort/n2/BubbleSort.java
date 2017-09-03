package com.ttrlalgs.algorithm.sort.n2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import com.google.common.base.Preconditions;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.Sort;

public class BubbleSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be empty.");
        Preconditions.checkArgument(Objects.nonNull(comparator), "Comparator cannot be null.");

        return sort(collection, (T t, T t2) -> comparator.compare(t, t2) > 0);
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be empty.");

        return sort(collection, (T t, T t2) -> t.compareTo(t2) > 0);
    }

    private <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        if (check(collection))
            return collection;

        @SuppressWarnings("unchecked")
        T[] array = (T[]) collection.toArray();

        boolean wasSwap = true;
        while (wasSwap) {
            wasSwap = false;
            for (int current = 0; current < array.length - 1; current++) {
                int next = current + 1;
                if (isGreater.test(array[current], array[next])) {
                    SortUtils.swap(array, current, next);
                    wasSwap = true;
                }
            }
        }

        return Arrays.asList(array);
    }
}
