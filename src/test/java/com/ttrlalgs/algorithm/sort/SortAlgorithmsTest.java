package com.ttrlalgs.algorithm.sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ttrlalgs.algorithm.sort.n2.BubbleSort;
import com.ttrlalgs.algorithm.sort.n2.InsertionSort;
import com.ttrlalgs.algorithm.sort.n2.SelectionSort;
import com.ttrlalgs.algorithm.sort.nlogn.HeapSort;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SortAlgorithmsTest {

    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object> data() {
        return Arrays.asList(new Object[][]{
                {new InsertionSort(), "Insert Sort"},
                {new BubbleSort(), "Bubble Sort"},
                {new HeapSort(), "Heap Sort"},
                {new SelectionSort(), "Selection Sort"}
        });
    }

    private Sort sortAlg;

    public SortAlgorithmsTest(Sort sortAlg, String algName) {
        this.sortAlg = sortAlg;
    }

    @Test
    public void sort_Comparable_Success() throws Exception {
        Collection<Integer> sorted = sortAlg.sort(SortTestUtils.getShuffledCollection(-5, 10, 10));

        assertThat(SortTestUtils.isSorted(sorted)).isTrue();
    }

    @Test
    public void sort_Comparator_Success() throws Exception {
        Collection<Integer> sorted = sortAlg.sort(SortTestUtils.getShuffledCollection(-5, 10, 10), Comparator.naturalOrder());

        assertThat(SortTestUtils.isSorted(sorted, Comparator.naturalOrder())).isTrue();
    }

    @Test
    public void sort_ComparatorBroken_Failed() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Collection<Integer> sorted = sortAlg.sort(SortTestUtils.getShuffledCollection(-5, 10, 10),
                (o1, o2) -> random.nextInt(-1, 2));

        assertThat(SortTestUtils.isSorted(sorted)).isFalse();
    }
}