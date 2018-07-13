package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Depth First Search pathTo implementation.
 * Only detects that path to Vertex exit. It doesn't mean to be the shortest path.
 * Negative weights not supported.
 */
public class DFS<V> extends GraphPathSearch<V> {

    public DFS(List<Edge<V>> edges, Vertex<V> startVertex) {
        super(edges, startVertex);
    }

    @Override
    protected Collector<V> collector() {

        return new Collector<>() {
            Queue<Vertex<V>> queue = new ArrayDeque<>();

            @Override
            public void accept(Vertex<V> vertex) {
                queue.add(vertex);
            }

            @Override
            public Vertex<V> getNext() {
                return queue.poll();
            }

            @Override
            public void init(Vertex<V> startVertex) {
                queue.add(startVertex);
            }

            @Override
            public boolean isEmpty() {
                return queue.isEmpty();
            }
        };
    }
}
