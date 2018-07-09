package com.ttrlalgs.challenge;

public class HasOnlyUniqueCharacters {

    // todo: move to tests section with proper testing.
    public static void main(String[] args) {
        System.out.println("Answer: " + hasOnlyUniqueCharacters("abcdefgz"));
        System.out.println("Answer: " + hasOnlyUniqueCharacters("abcdefgza"));
    }

    /**
     * For lower case only. Using array will be more easy.
     * To have upper+lower+numbers removing unnecessary symbols will be needed.
     */
    public static boolean hasOnlyUniqueCharacters(String value) {
        char[] chars = value.toLowerCase().toCharArray();
        int check = 0;

        for (char c : chars) {
            int tmp = check;
            check |= 1 << c - 65;
            if (tmp == check) {
                System.out.println("Char [" + c + "] is a duplicate.");
                return false;
            }
        }
        return true;
    }
}
