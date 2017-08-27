package com.ttrlalgs.algorithm.sort;

import java.util.Collection;
import java.util.Comparator;

/**
 * Base sort interface.
 * {@param collection} - should not contain {@code nulls}.
 */
public interface Sort {

    <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator);

    <T extends Comparable<T>> Collection<T> sort(Collection<T> collection);

    default <T> boolean check(Collection<T> collection) {
        return collection.isEmpty();
    }
}
