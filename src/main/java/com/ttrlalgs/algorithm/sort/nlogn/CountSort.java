package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import javax.annotation.Nullable;
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

        // checks that all elements are Integers.
        // restricted by max array length in Java.
        collection.stream()
                .filter(e -> !(e instanceof Integer))
                .findFirst()
                .ifPresent(e -> {
                    throw new UnsupportedOperationException("Count Sort supports only Integers");
                });

        int[] storage = new int[collection.size()];

        collection.stream()
                .map(e -> (Integer) e)
                .forEach(e -> storage[e] += 1);

        int[] sorted = new int[storage.length];

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

    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        Preconditions.checkArgument(Objects.nonNull(collection), "Collection cannot be null.");

        return sort(collection, Comparator.naturalOrder());
    }
}
