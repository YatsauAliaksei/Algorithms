package com.ttrlalgs.util;

import com.ttrlalgs.structure.Node;
import org.junit.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeUtilTest {

    @Test
    public void createBalanced() {
        Node<Integer> balancedTree = TreeUtil.createBalanced(IntStream.range(0, 7).boxed().collect(toList()));
        assertThat(balancedTree.value()).isEqualTo(3);

        // 1st lvl
        Node<Integer> left = balancedTree.left();
        Node<Integer> right = balancedTree.right();
        assertThat(left.value()).isEqualTo(1);
        assertThat(right.value()).isEqualTo(5);

        // 2nd lvl
        assertThat(left.left().value()).isEqualTo(0);
        assertThat(left.right().value()).isEqualTo(2);
        assertThat(right.left().value()).isEqualTo(4);
        assertThat(right.right().value()).isEqualTo(6);
    }
}