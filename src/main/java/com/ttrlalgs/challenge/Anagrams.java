package com.ttrlalgs.challenge;

public class Anagrams {
    public static void main(String[] args) {
        int i = numberNeeded("azckzt", "aztruklm");
        System.out.println(i);
    }

    private static int numberNeeded(String a, String b) {
        int[] mapA = createMap(a);
        int[] mapB = createMap(b);

        int toRemove = 0;
        for (int i = 0; i < mapA.length; i++) {
            if (mapA[i] != mapB[i]) {
                toRemove += Math.abs(mapA[i] - mapB[i]);
            }
        }
        return toRemove;
    }

    private static int[] createMap(String str) {
        int[] map = new int[26];
        char[] chars = str.toCharArray();
        for (char c : chars) {
            map[(int) c - 97] += 1;
        }
        return map;
    }
}
