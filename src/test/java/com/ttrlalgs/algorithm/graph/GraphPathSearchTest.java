package com.ttrlalgs.algorithm.graph;

import com.ttrlalgs.structure.graph.Edge;
import com.ttrlalgs.structure.graph.Vertex;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GraphPathSearchTest {

    @Test
    public void pathToBFS() {
        List<Edge<Integer>> edges = getEdges();
        BFS<Integer> bfs = new BFS<>(edges, Vertex.of(1));
        List<Vertex<Integer>> vertices = bfs.pathTo(Vertex.of(6));

        assertThat(vertices).containsExactly(Vertex.of(1), Vertex.of(2), Vertex.of(3), Vertex.of(4));
        System.out.println(vertices);
    }

    @Test
    public void pathToDijkstras() {
        List<Edge<Integer>> edges = getEdges();
        Dijkstras<Integer> dijkstras = new Dijkstras<>(edges, Vertex.of(1));
        List<Vertex<Integer>> vertices = dijkstras.pathTo(Vertex.of(6));

        assertThat(vertices).containsExactly(Vertex.of(1), Vertex.of(2), Vertex.of(3), Vertex.of(4));
        System.out.println(vertices);
    }

    private List<Edge<Integer>> getEdges() {
        return List.of(
                Edge.of(Vertex.of(1), Vertex.of(2), 1),
                Edge.of(Vertex.of(2), Vertex.of(3), 1),
                Edge.of(Vertex.of(3), Vertex.of(4), 1),
                Edge.of(Vertex.of(4), Vertex.of(6), 3),
                Edge.of(Vertex.of(3), Vertex.of(5), 4),
                Edge.of(Vertex.of(5), Vertex.of(6), 1));
    }
}