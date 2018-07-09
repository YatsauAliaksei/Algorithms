package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Vertex;

import java.util.function.Consumer;

public interface Collector<V> extends Consumer<Vertex<V>> {

    void init(Vertex<V> startVertex);

    Vertex<V> getNext();

    boolean isEmpty();
}
