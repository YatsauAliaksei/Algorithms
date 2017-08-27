package com.ttrlalgs.algorithm;

import com.google.common.base.Preconditions;

public class GreaterCommonDivisor {

    /**
     * Finds GDC of 2 longs.
     * Euclid algorithm.
     * @return - GDC or -1 if none
     */
    public static long getGreaterCommonDivisor(long l, long k) {
        Preconditions.checkArgument(l != 0 && k != 0, "One or both arguments are 0.");
        if (l == k)
            return l;

        long greater = l > k ? l : k;
        long lower = l > k ? k : l;

        return getGDC(greater, lower);
    }

    private static long getGDC(long greater, long lower) {
        long mod = greater % lower;
        if (mod == 1)
            return -1;

        if (mod == 0) {
            return lower;
        } else
            return getGDC(lower, mod);
    }
}
