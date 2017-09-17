package com.ttrlalgs.algorithm.sort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Parameterized;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.parameterized.BlockJUnit4ClassRunnerWithParameters;
import org.junit.runners.parameterized.ParametersRunnerFactory;
import org.junit.runners.parameterized.TestWithParameters;
import com.ttrlalgs.algorithm.sort.n2.BubbleSort;
import com.ttrlalgs.algorithm.sort.n2.InsertionSort;
import com.ttrlalgs.algorithm.sort.n2.SelectionSort;
import com.ttrlalgs.algorithm.sort.nlogn.CountSort;
import com.ttrlalgs.algorithm.sort.nlogn.HeapSort;
import com.ttrlalgs.algorithm.sort.nlogn.MergeSort;
import com.ttrlalgs.algorithm.sort.nlogn.QuickSort;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(SortAlgorithmsTest.RunnerFactoryWithExclusions.class)
public class SortAlgorithmsTest {

    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object> data() {
        return Arrays.asList(new Object[][]{
                {new InsertionSort(), "Insert Sort", -1000, 1000},
                {new BubbleSort(), "Bubble Sort", -1000, 1000},
                {new HeapSort(), "Heap Sort", -1000, 1000},
                {new QuickSort(), "Quick Sort", -1000, 1000},
                {new SelectionSort(), "Selection Sort", -1000, 1000},
                {new MergeSort(), "Merge Sort", -1000, 1000},
                {new CountSort(), "Count Sort", 0, 100},
        });
    }

    private Sort sortAlg;
    private int from;
    private int to;
    private int size = 10_000;

    public SortAlgorithmsTest(Sort sortAlg, String algName, int from, int to) {
        this.sortAlg = sortAlg;
        this.from = from;
        this.to = to;
    }

    @Test
    public void sort_Comparable_Success() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(from, to, size);
        List<Integer> copy = new ArrayList<>(shuffledCollection); // in case algorithm transforms original collection. F.i. Selection sort.
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection);

        assertThat(SortTestUtils.isSorted(sorted)).isTrue();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }

    @Test
    public void sort_Comparator_Success() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(from, to, size);
        List<Integer> copy = new ArrayList<>(shuffledCollection);
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection, Comparator.naturalOrder());

        assertThat(SortTestUtils.isSorted(sorted, Comparator.naturalOrder())).isTrue();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }

    @Test
    @Exclude("Count Sort")
    public void sort_ComparatorBroken_Failed() throws Exception {
        List<Integer> shuffledCollection = SortTestUtils.getShuffledCollection(from, to, size);
        List<Integer> copy = new ArrayList<>(shuffledCollection);
        Collections.copy(copy, shuffledCollection);

        Collection<Integer> sorted = sortAlg.sort(shuffledCollection, (o1, o2) -> o1.compareTo(o2) * -1); // reverse

        boolean isSorted = SortTestUtils.isSorted(sorted);
        if (isSorted)
            System.out.println("Sorted: " + sorted);
        assertThat(isSorted).isFalse();
        assertThat(sorted).containsOnlyElementsOf(copy);
    }


    public static class RunnerFactoryWithExclusions implements ParametersRunnerFactory {

        public Runner createRunnerForTestWithParameters(TestWithParameters test) throws InitializationError {
            return new BlockJUnit4ClassRunnerWithParameters(test) {

                @Override
                protected boolean isIgnored(FrameworkMethod child) {
                    Exclude exclude = child.getAnnotation(Exclude.class);
                    if (exclude != null) {
                        for (String s : exclude.value()) {
                            if (("[" + s + "]").equalsIgnoreCase(test.getName()))
                                return true;
                        }
                        int param = exclude.param();
                        if (param > -1) {
                            Object o = test.getParameters().get(param);
                            if (o != null) {
                                return o.equals(true);
                            }
                        }
                    }
                    return super.isIgnored(child);
                }
            };
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Exclude {
        String[] value() default {};

        int param() default -1;
    }
}