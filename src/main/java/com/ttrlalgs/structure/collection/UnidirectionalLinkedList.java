package com.ttrlalgs.structure.collection;

public class UnidirectionalLinkedList<T> extends AbstractLinkedList<T> {

    public void add(T value) {
        size++;
        if (first == null) {
            first = new Node<>(value);
            last = first;
            return;
        }
        last.next = new Node<>(value);
        last = last.next;
    }
}
