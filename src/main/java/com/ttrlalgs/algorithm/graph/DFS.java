package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Depth First Search pathTo implementation.
 * Only detects that path to Vertex exit. It doesn't mean to be the shortest path.
 * Negative weights not supported.
 */
public final class DFS<V> extends GraphPathSearch<V> {

    public DFS(List<Edge<V>> edges, Vertex<V> startVertex) {
        super(edges, startVertex);
    }

    @Override
    protected Collector<V> collector() {

        return new Collector<>() {
            Stack<Vertex<V>> stack = new Stack<>();

            @Override
            public void accept(Vertex<V> vertex) {
                stack.push(vertex);
            }

            @Override
            public Vertex<V> getNext() {
                return stack.pop();
            }

            @Override
            public void init(Vertex<V> startVertex) {
                stack.push(startVertex);
            }

            @Override
            public boolean isEmpty() {
                return stack.isEmpty();
            }
        };
    }
}
