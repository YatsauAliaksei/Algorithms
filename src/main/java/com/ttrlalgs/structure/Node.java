package com.ttrlalgs.structure;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left, right;
    private int size;

    public void value(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public void left(Node<T> left) {
        this.left = left;
    }

    public Node<T> left() {
        return left;
    }

    public void right(Node<T> right) {
        this.right = right;
    }

    public Node<T> right() {
        return right;
    }

    public void size(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }
}

