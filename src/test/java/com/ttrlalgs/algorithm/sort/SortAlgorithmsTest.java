package com.ttrlalgs.algorithm.sort;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.ttrlalgs.algorithm.sort.n2.BubbleSort;
import com.ttrlalgs.algorithm.sort.n2.InsertionSort;
import com.ttrlalgs.algorithm.sort.n2.SelectionSort;
import com.ttrlalgs.algorithm.sort.nlogn.HeapSort;
import com.ttrlalgs.algorithm.sort.nlogn.MergeSort;
import com.ttrlalgs.algorithm.sort.nlogn.QuickSort;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SortAlgorithmsTest {

    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object> data() {
        return Arrays.asList(new Object[][]{
                {new InsertionSort(), "Insert Sort"},
                {new BubbleSort(), "Bubble Sort"},
                {new HeapSort(), "Heap Sort"},
                {new QuickSort(), "Quick Sort"},
                {new SelectionSort(), "Selection Sort"},
                {new MergeSort(), "Merge Sort"}
        });
    }

    private Sort sortAlg;

    public SortAlgorithmsTest(Sort sortAlg, String algName) {
        this.sortAlg = sortAlg;
    }

    @Test
    public void sort_Comparable_Success() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(-100, 1000, 100);
        List<Integer> copy = new ArrayList<>(shuffledCollection); // in case algorithm transforms original collection. F.i. Selection sort.
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection);

        assertThat(SortTestUtils.isSorted(sorted)).isTrue();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }

    @Test
    public void sort_Comparator_Success() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(-100, 1000, 100);
        List<Integer> copy = new ArrayList<>(shuffledCollection);
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection, Comparator.naturalOrder());

        assertThat(SortTestUtils.isSorted(sorted, Comparator.naturalOrder())).isTrue();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }

    @Test
    public void sort_ComparatorBroken_Failed() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(-100, 1000, 100);
        List<Integer> copy = new ArrayList<>(shuffledCollection);
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection, (o1, o2) -> o1.compareTo(o2) * -1); // reverse

        boolean isSorted = SortTestUtils.isSorted(sorted);
        if (isSorted)
            System.out.println("Sorted: " + sorted);
        assertThat(isSorted).isFalse();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }
}