package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiPredicate;
import com.ttrlalgs.algorithm.sort.AbsSort;

public class MergeSort extends AbsSort {

    @Override
    @SuppressWarnings("unchecked")
    protected <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        T[] arr = (T[]) collection.toArray();
        sort(arr, 0, arr.length, isGreater);
        return Arrays.asList(arr);
    }

    private <T> void sort(T[] array, int start, int end, BiPredicate<T, T> isGreater) {
        int itemsNumber = end - start;
        if (itemsNumber <= 1) return;

        int center = (start + end) / 2;
        sort(array, start, center, isGreater);
        sort(array, center, end, isGreater);

        int left = start;
        int right = center;
        Object[] storage = new Object[itemsNumber];

        for (int i = 0; i < storage.length; i++) {

            T leftItem = null;
            T rightItem = null;
            T lo;
            if (left != center &&
                    (right == end || isGreater.test(rightItem = array[right], leftItem = array[left]))) { // late array access.
                if (leftItem == null)
                    leftItem = array[left];

                lo = leftItem;
                if (left < center)
                    left++;
            } else {
                if (rightItem == null)
                    rightItem = array[right];

                lo = rightItem;
                right++;
            }

            storage[i] = lo;
        }

        System.arraycopy(storage, 0, array, start, storage.length);
    }
}
