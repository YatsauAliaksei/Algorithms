package com.ttrlalgs.challenge;

import java.util.Stack;

public class BracersPair {

    private static char a1 = '[';
    private static char a2 = ']';

    private static char b1 = '(';
    private static char b2 = ')';

    private static char c1 = '{';
    private static char c2 = '}';

    public static boolean isBalanced(String expression) {

        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            if (isAPair(stack.peek(), c)) {
                stack.pop();
            } else
                stack.push(c);
        }
        return stack.isEmpty();
    }

    private static boolean isAPair(char a, char b) {
        int i = Math.abs((int) a - (int) b);
        return i == 1 || i == 2;
    }
}
