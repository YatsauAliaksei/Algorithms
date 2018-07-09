package com.ttrlalgs.structure.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@Getter
@ToString
public class Vertex<V> {
    private final V value;
    private final String unique; // in case Vertex with same value should be treated as equals.

    public static <V> Vertex<V> of(V value) {
        return new Vertex<>(value, value.toString());
    }
}
