package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Generic bread first search algorithm implementation.
 */
abstract class GraphPathSearch<V> {

    private final Vertex<V> startVertex;
    private final List<Edge<V>> edges;

    private final Map<Vertex<V>, Boolean> visited = new HashMap<>();
    private final Map<Vertex<V>, Vertex<V>> pathTo = new HashMap<>();

    @Getter(AccessLevel.PROTECTED)
    private final Map<Vertex<V>, Double> weightToEdge = new HashMap<>();
    @Getter(AccessLevel.PROTECTED)
    private final Map<Vertex<V>, List<Edge<V>>> vertexEdgesMap = new HashMap<>();

    public GraphPathSearch(List<Edge<V>> edges, Vertex<V> startVertex) {
        this.edges = edges;
        this.startVertex = startVertex;
        init();
    }

    private void init() {
        vertexEdgesMap.putAll(edges.stream()
                .collect(groupingBy(Edge::getStart)));

        initWeight();

        Collector<V> collector = collector();
        collector.init(startVertex);

        while (!collector.isEmpty()) {
            Vertex<V> vertex = collector.getNext();

            if (visited.putIfAbsent(vertex, true) != null)
                continue;

            List<Edge<V>> edges = vertexEdgesMap.get(vertex);
            if (edges == null)
                continue;

            Double parentWeight = weightToEdge.get(vertex);
            for (Edge<V> edge : edges) {
                Vertex<V> vertexEnd = edge.getEnd();
                collector.accept(vertexEnd);

                Double currentWeight = weightToEdge.get(vertexEnd);
                double newWayWeight = parentWeight + edge.getWeight();
                if (currentWeight == null || currentWeight > newWayWeight) {
                    pathTo.put(vertexEnd, vertex);
                    weightToEdge.put(vertexEnd, newWayWeight);
                }
            }
        }
    }

    public List<Vertex<V>> pathTo(Vertex<V> vertex) {
        List<Vertex<V>> path = new ArrayList<>();

        Vertex<V> tmp = vertex;
        Vertex<V> v;
        while ((v = pathTo.get(tmp)) != null) {
            path.add(v);
            tmp = v;
        }

        Collections.reverse(path);
        return path;
    }

    protected abstract Collector<V> collector();

    private void initWeight() {
        List<Vertex<V>> vertices = edges.stream()
                .flatMap(e -> Stream.of(e.getStart(), e.getEnd()))
                .distinct()
                .collect(toList());

        vertices.forEach(v -> weightToEdge.put(v, Double.MAX_VALUE));

        this.weightToEdge.put(startVertex, .0);
    }
}
