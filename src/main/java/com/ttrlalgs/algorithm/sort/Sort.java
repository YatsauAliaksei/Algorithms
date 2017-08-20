package com.ttrlalgs.algorithm.sort;

import java.util.Collection;
import java.util.Comparator;

public interface Sort {

    <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator);

    <T extends Comparable> Collection<T> sort(Collection<T> collection);
}
