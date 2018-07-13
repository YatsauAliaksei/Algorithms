package com.ttrlalgs.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlgUtilsTest {

    @Test
    public void factorial() {
        long factorial = AlgUtils.factorial(11);
        assertThat(factorial).isEqualTo(39916800);
    }

    @Test
    public void fibonacci() {
        long fibonacci = AlgUtils.fibonacci(12);
        assertThat(fibonacci).isEqualTo(232);
    }

    @Test
    public void numberOfVariantUnique() {
        long l = AlgUtils.numberOfVariantUnique(3, 5);
        assertThat(l).isEqualTo(10);
    }

    @Test
    public void numberOfVariantNotUnique() {
        long l = AlgUtils.numberOfVariantNotUnique(3, 5);
        assertThat(l).isEqualTo(35);
    }

    @Test
    public void permutation() {
        long permutations = AlgUtils.permutation(1234);
        assertThat(permutations).isEqualTo(24);

        permutations = AlgUtils.permutation(1244);
        assertThat(permutations).isEqualTo(12);
    }
}