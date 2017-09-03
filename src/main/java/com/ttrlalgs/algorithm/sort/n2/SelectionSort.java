package com.ttrlalgs.algorithm.sort.n2;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.ttrlalgs.algorithm.sort.Sort;

public class SelectionSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        return sort(collection, (BiFunction<T, T, Integer>) comparator::compare);
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        return sort(collection, (BiFunction<T, T, Integer>) Comparable::compareTo);
    }

    private <T> Collection<T> sort(Collection<T> collection, BiFunction<T, T, Integer> isGreater) {
        if (check(collection))
            return collection;

        return Stream.generate(() -> takeOutMin(collection, isGreater))
                .limit(collection.size())
                .collect(Collectors.toList());
    }

    private <T> T takeOutMin(Collection<T> collection, BiFunction<T, T, Integer> isGreater) {
        T min = collection.stream()
                .min(isGreater::apply).orElseThrow(IndexOutOfBoundsException::new);
        collection.remove(min);
        return min;
    }
}
