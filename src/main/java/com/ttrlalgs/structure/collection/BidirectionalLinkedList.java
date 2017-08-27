package com.ttrlalgs.structure.collection;

import java.util.Iterator;

public class BidirectionalLinkedList<T> extends AbstractLinkedList<T> {

    public void add(T value) {
        size++;
        if (first == null) {
            first = new BiNode<>(value);
            last = first;
            return;
        }
        BiNode<T> node = new BiNode<>(value);
        last.next = node;
        node.prev = (BiNode<T>) last;
        last = last.next;
    }


    public Iterator<T> reverseIterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T> {
        BiNode<T> cur;
        BiNode<T> prev;
        BiNode<T> last = (BiNode<T>) BidirectionalLinkedList.super.first;

        @Override
        public boolean hasNext() {
            if (last == null)
                return false;

            if (cur == null) {
                cur = last;
                return true;
            }

            prev = cur;
            cur = cur.prev;

            return cur != null;
        }

        @Override
        public void remove() {
            if (size == 0)
                return;

            if (prev == null) {
                last.prev.next = null;
                return;
            }

            prev.prev = cur.prev;
        }

        @Override
        public T next() {
            return cur.value;
        }
    }

    private class BiNode<U> extends Node<U> {
        BiNode<U> prev;

        private BiNode(U value) {
            super(value);
        }
    }
}
