package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;
import com.google.common.base.Preconditions;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.AbsSort;

public class QuickSort extends AbsSort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        Preconditions.checkArgument(!collection.contains(null), "Collection cannot contain Nulls");

        return sort(collection, (T t, T t2) -> comparator.compare(t, t2) >= 0);
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        Preconditions.checkArgument(!collection.contains(null), "Collection cannot contain Nulls");

        return sort(collection, (T t, T t2) -> t.compareTo(t2) >= 0);
    }

    @SuppressWarnings("unchecked")
    protected <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        T[] arr = (T[]) collection.toArray();
        sort(arr, 0, arr.length, isGreater);
        return Arrays.asList(arr);
    }

    private <T> void sort(T[] arr, int start, int end, BiPredicate<T, T> isGreater) {
        if (start >= end) return;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int splitElementIndex = random.nextInt(start, end);

        for (int i = start; i < end; i++) {
            boolean greater = isGreater.test(arr[i], arr[splitElementIndex]);

            if (greater && i < splitElementIndex) {
                SortUtils.swap(arr, i, splitElementIndex);
                splitElementIndex = i;
            } else if (!greater && i > splitElementIndex) {
                T tmp = arr[i];
                SortUtils.shiftRight(arr, start, i);
                arr[start] = tmp;
                splitElementIndex++;
            }
        }

        sort(arr, start, splitElementIndex, isGreater);
        sort(arr, splitElementIndex + 1, end, isGreater);
    }
}
