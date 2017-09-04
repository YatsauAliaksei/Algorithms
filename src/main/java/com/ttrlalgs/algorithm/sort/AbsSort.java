package com.ttrlalgs.algorithm.sort;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import com.google.common.base.Preconditions;

public abstract class AbsSort implements Sort {

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

    protected abstract <T> Collection<T> sort(Collection<T> collection, BiPredicate<T, T> isGreater);
}
