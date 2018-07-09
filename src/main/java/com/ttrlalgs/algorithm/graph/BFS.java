package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;

import java.util.List;
import java.util.Stack;

/**
 * Bread First Search pathTo implementation.
 * Negative weights not supported.
 */
public class BFS<V> extends GraphPathSearch<V> {

    public BFS(List<Edge<V>> edges, Vertex<V> startVertex) {
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
