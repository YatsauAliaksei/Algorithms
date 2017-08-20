package com.ttrlalgs.algorithm.sort;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BubbleSortTest {

    private Sort bubbleSort = new BubbleSort();

    @Test
    public void sort_Comparable_Success() throws Exception {
        List<Integer> collection = getShuffledCollection(0, 10);

        Collection<Integer> sorted = bubbleSort.sort(collection);

        assertThat(SortTestUtils.isSorted(sorted)).isTrue();
    }

    @Test
    public void sort_Comparator_Success() throws Exception {
        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2)
                return -1;
            return 0;
        };

        List<Integer> collection = getShuffledCollection(10, 20);

        Collection<Integer> sorted = bubbleSort.sort(collection, comparator);

        assertThat(SortTestUtils.isSorted(sorted, comparator)).isTrue();
    }

    private List<Integer> getShuffledCollection(int from, int to) {
        List<Integer> collection = IntStream.range(from, to)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(collection);
        return collection;
    }
}