package com.ttrlalgs.util;

import com.google.common.base.Preconditions;
import com.ttrlalgs.algorithm.sort.nlogn.MergeSort;
import com.ttrlalgs.structure.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeUtil {

    public static <T extends Comparable<T>> Node<T> createBalanced(List<T> data) {
        Preconditions.checkArgument(data != null && data.size() > 0);

        MergeSort mergeSort = new MergeSort();
        List<T> sorted = new ArrayList<>(mergeSort.sort(data));

        Node<T> root = new Node<>();
        fillMedian(sorted, root, 0, sorted.size() - 1);

        return root;
    }

    private static <T extends Comparable<T>> Node<T> fillMedian(List<T> data, Node<T> root, int start, int end) {
        if (start > end)
            return null;

        int center = start + ((end - start + 1) / 2);
        root.value(data.get(center));
        root.size(end - start);

        root.left(fillMedian(data, new Node<>(), start, center - 1));
        if (center - end != 0)
            root.right(fillMedian(data, new Node<>(), center + 1, end));

        return root;
    }
}
