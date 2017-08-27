package com.ttrlalgs.algorithm.prime;

import java.util.List;
import org.junit.Test;
import com.ttrlalgs.algorithm.number.NumberUtils;

public class NumberUtilsTest {

    @Test
    public void findDelimiters_Success() throws Exception {
        List<Long> delimiters = NumberUtils.findFactors(242);
        System.out.println(delimiters);
    }

}