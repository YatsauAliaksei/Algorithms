package com.ttrlalgs.structure.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode
@Getter
public class Edge<V> {
    private final Vertex<V> start;
    private final Vertex<V> end;
    private final double weight;
}
