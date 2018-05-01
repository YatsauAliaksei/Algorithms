package com.ttrlalgs.algorithm.sort.nlogn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import com.google.common.base.Preconditions;
import com.ttrlalgs.algorithm.sort.Sort;

import static java.util.stream.Collectors.toList;

public class CountSort implements Sort {

    /**
     * Restriction:
     * - Supports only collection of Integers.
     * {@link Byte} & {@link Short} as well fit to restriction but are used very rarely.
     * @param comparator - not used. Nullable.
     * @param <T>        - {@link Integer}
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<T> sort(Collection<T> collection, @Nullable Comparator<T> comparator) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be null.");
        Preconditions.checkArgument(Objects.nonNull(comparator), "Comparator cannot be null.");
        if (check(collection))
            return collection;

        int max = getMaxElement(collection);

        int[] storage = new int[max + 1];

        collection.stream()
                .map(e -> (Integer) e)
                .forEach(e -> storage[e] += 1);

        int[] sorted = new int[collection.size()];

        int cursor = 0;
        for (int i = 0; i < storage.length; i++) {
            int numberOfEntries = storage[i];
            if (numberOfEntries > 0) {
                while (numberOfEntries-- > 0)
                    sorted[cursor++] = i;
            }
        }

        return (Collection<T>) Arrays.stream(sorted)
                .boxed().collect(toList());
    }

    private <T> int getMaxElement(Collection<T> collection) {
        // checks that all elements are Integers.
        // restricted by max array length in Java.
        int max = -1;
        for (T e : collection) {
            if (!(e instanceof Integer)) {
                throw new UnsupportedOperationException("Count Sort supports only Integers");
            }

            int i = (Integer) e;

            if(i < 0)
                throw new UnsupportedOperationException("Supports only positive values.");

            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be null.");

        return sort(collection, Comparator.naturalOrder());
    }
}
