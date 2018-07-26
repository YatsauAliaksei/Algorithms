package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Dijkstras algorithm implementation.
 * Negative weights not supported.
 */
public final class Dijkstras<V> extends GraphPathSearch<V> {

    public Dijkstras(List<Edge<V>> edges, Vertex<V> startVertex) {
        super(edges, startVertex);
    }

    @Override
    protected Collector<V> collector() {
        return new Collector<>() {

            private PriorityQueue<Vertex<V>> vertices = new PriorityQueue<>((o1, o2) -> {
                Map<Vertex<V>, Double> weightToEdge = getWeightToEdge();
                Double o1Weight = weightToEdge.get(o1);
                Double o2Weight = weightToEdge.get(o2);

                return o1Weight.compareTo(o2Weight);
            });

            @Override
            public void init(Vertex<V> startVertex) {
                vertices.add(startVertex);
            }

            @Override
            public Vertex<V> getNext() {
                Vertex<V> vertex = vertices.poll();
                List<Edge<V>> edges = getVertexEdgesMap().get(vertex);
                if (edges != null) {
                    edges.stream()
                            .map(Edge::getEnd)
                            .filter(Objects::nonNull)
                            .forEach(vertices::add);
                }

                return vertex;
            }

            @Override
            public boolean isEmpty() {
                return vertices.isEmpty();
            }

            @Override
            public void accept(Vertex<V> vertex) {

            }
        };
    }
}
