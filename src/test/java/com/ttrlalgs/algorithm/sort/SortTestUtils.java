package com.ttrlalgs.algorithm.sort;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

                if (isGreater.test(current, next))
                    return false;
            }
        }
        return true;
    }

    public static List<Integer> getShuffledCollection(int low, int upper, int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Integer> collection = IntStream.generate(() -> random.nextInt(low, upper))
                .limit(size)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(collection);
        return collection;
    }
}
