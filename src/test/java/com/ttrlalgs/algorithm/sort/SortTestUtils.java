package com.ttrlalgs.algorithm.sort;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiPredicate;

public class SortTestUtils {

    public static <T> boolean isSorted(Collection<T> collection, Comparator<T> comparator) {
        return isSorted(collection, (T t, T t2) -> comparator.compare(t, t2) > 0);
    }

    public static <T extends Comparable> boolean isSorted(Collection<T> collection) {
        return isSorted(collection, (T t, T t2) -> t.compareTo(t2) > 0);
    }

    private static <T> boolean isSorted(Collection<T> collection, BiPredicate<T, T> isGreater) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (iterator.hasNext()) {
                T next = iterator.next();

                if (isGreater.test(current, next)) return false;
            }
        }
        return true;
    }
}
