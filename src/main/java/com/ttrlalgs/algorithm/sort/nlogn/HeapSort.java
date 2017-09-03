package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import com.google.common.base.Preconditions;
import com.ttrlalgs.algorithm.SortUtils;
import com.ttrlalgs.algorithm.sort.Sort;

public class HeapSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be empty.");
        Preconditions.checkArgument(Objects.nonNull(comparator), "Comparator cannot be null.");
        Preconditions.checkArgument(!collection.contains(null), "Collection cannot contain Nulls");

        return sort(collection, (T t, T t2) -> comparator.compare(t, t2) > 0);
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be empty.");
        Preconditions.checkArgument(!collection.contains(null), "Collection cannot contain Nulls");

        return sort(collection, (T t, T t2) -> t.compareTo(t2) > 0);
    }

    @SuppressWarnings("unchecked")
    private <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater) {
        Object[] heap = new Object[collection.size()];

        int lastElementIndex = 0;
        for (T element : collection) {
            heap[lastElementIndex] = element;

            int currentElementIndex = lastElementIndex++;
            int parentIndex;
            while (isGreater.test((T) heap[currentElementIndex], (T) heap[parentIndex = (currentElementIndex - 1) / 2])) {
                SortUtils.swap(heap, parentIndex, currentElementIndex);
                currentElementIndex = parentIndex;
            }
        }

        for (int i = 0; i < heap.length; i++) {
            SortUtils.swap(heap, 0, heap.length - 1 - i);
            fixHeap(heap, 0, heap.length - 1 - i, isGreater);
        }

        return (Collection<T>) Arrays.asList(heap);
    }

    @SuppressWarnings("unchecked")
    private <T> void fixHeap(Object[] heap, int startIndex, int endIndex, BiPredicate<T, T> isGreater) {
        do {
            int child = startIndex * 2 + 1;
            if (child >= endIndex) {
                break;
            }
            int child2 = startIndex * 2 + 2;
            if (child2 < endIndex) {
                child = isGreater.test((T) heap[child], (T) heap[child2]) ? child : child2;
            }

            if (isGreater.test((T) heap[child], (T) heap[startIndex])) {
                SortUtils.swap(heap, child, startIndex);
                startIndex = child;
            } else
                break;

        } while (true);
    }
}
