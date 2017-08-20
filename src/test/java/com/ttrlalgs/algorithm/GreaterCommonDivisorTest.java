package com.ttrlalgs.algorithm;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GreaterCommonDivisorTest {

    @Test
    public void getGreaterCommonDivisor_Found() throws Exception {
        int g = 231 * 21;
        int l = 231 * 8;
        checkFound(g, l, 231);
    }

    @Test
    public void getGreaterCommonDivisor_SwappedParams_Found() throws Exception {
        int g = 217 * 21;
        int l = 217 * 17;
        checkFound(l, g, 217);
    }

    @Test
    public void getGreaterCommonDivisor_NotFound() throws Exception {
        long gcd = GreaterCommonDivisor.getGreaterCommonDivisor(4851, 3004);
        assertThat(gcd).isEqualTo(-1);
    }

    private void checkFound(int l, int k, int result) {
        long gcd = GreaterCommonDivisor.getGreaterCommonDivisor(l, k);

        assertThat(l % gcd).isEqualTo(0);
        assertThat(k % gcd).isEqualTo(0);
        assertThat(gcd).isEqualTo(result);
    }
}