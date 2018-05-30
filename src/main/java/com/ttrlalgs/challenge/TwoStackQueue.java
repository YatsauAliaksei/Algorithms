package com.ttrlalgs.challenge;

import java.util.Stack;

public class TwoStackQueue {

    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();
        int count;

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.push(value);
            count++;
        }

        public T peek() {
            if (refresh())
                return null;

            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            if (refresh())
                return null;

            count--;
            return stackOldestOnTop.pop();
        }

        private boolean refresh() {
            if (count == 0)
                return true;

            if (stackOldestOnTop.isEmpty()) {
                for (int i = 0; i < count; i++) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                }

                while (!stackNewestOnTop.isEmpty()) // clearance
                    stackNewestOnTop.pop();
            }
            return false;
        }
    }
}
