package com.ttrlalgs.structure.collection;

import java.util.Collection;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractLinkedList<T> implements Iterable<T> {
    protected Node<T> first;
    protected Node<T> last;
    protected int size;

    public boolean remove(T value) {
        boolean isNull = value == null;

        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {

            T v = iterator.next();
            if (isNull && v == null) {
                iterator.remove();
                size--;
                return true;
            }
            if (isNull ^ v == null)
                continue;

            if (v.equals(value)) {
                iterator.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(T value) {
        boolean isNullValue = value == null;
        for (T t : this) {
            if (isNullValue && t == null)
                return true;

            if (t == null)
                continue;

            if (t.equals(value))
                return true;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean containsAll(Collection<T> collection) {
        for (T t : collection) {
            if (!contains(t)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BaseIterator();
    }

    protected class Node<U> {
        protected Node(U value) {
            this.value = value;
        }

        U value;
        Node<U> next;
    }

    private class BaseIterator implements Iterator<T> {
        Node<T> cur;
        Node<T> prev;

        @Override
        public boolean hasNext() {
            if (first == null)
                return false;

            if (cur == null) {
                cur = first;
                return true;
            }

            prev = cur;
            cur = cur.next;

            return cur != null;
        }

        @Override
        public void remove() {
            if (size == 0)
                return;

            if (prev == null) {
                first = first.next;
                return;
            }

            prev.next = cur.next;
        }

        @Override
        public T next() {
            return cur.value;
        }
    }
}
