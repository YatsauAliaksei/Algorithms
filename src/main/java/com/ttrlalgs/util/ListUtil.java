package com.ttrlalgs.util;

import com.google.common.base.Preconditions;

import java.util.Collection;

public class ListUtil {

    /**
     * For even {@param data} size will return greater element.
     * returns index of Median element for sorted collection.
     */
    public static <T extends Comparable<T>> int takeMedianElementSorted(Collection<T> data) {
        Preconditions.checkArgument(data != null && data.size() > 0, "Data should have elements");

        return data.size() / 2;
    }
}
