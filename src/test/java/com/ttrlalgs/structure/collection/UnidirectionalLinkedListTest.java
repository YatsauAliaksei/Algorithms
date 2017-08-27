package com.ttrlalgs.structure.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnidirectionalLinkedListTest {

    @Test
    public void add() throws Exception {
        UnidirectionalLinkedList<String> list = new UnidirectionalLinkedList<>();
        for (String s : list) {
            throw new RuntimeException();
        }

        populate(list);

        list.forEach(System.out::println);
        assertThat(list).contains("one", "two", "three", null);
    }

    @Test
    public void remove() {
        UnidirectionalLinkedList<String> list = new UnidirectionalLinkedList<>();
        populate(list);

        list.remove(null);
        assertThat(list).containsExactly("one", "two", "three", null);

        list.remove("two");
        assertThat(list).containsExactly("one", "three", null);

        list.remove("one");
        assertThat(list).containsExactly("three", null);
    }

    private void populate(UnidirectionalLinkedList<String> list) {
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(null);
        list.add(null);

        assertThat(list).containsExactly("one", "two", "three", null, null);
    }

}