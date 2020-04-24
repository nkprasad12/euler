package src.utils.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import src.utils.generators.Generator;
import src.utils.generators.Generators;

public class AdjacencyListGraph<T> implements Graph<T> {

  private final HashMap<T, ArrayList<Edge<T>>> graph = new HashMap<>();

  @Override
  public void addVertex(T vertex) {
    if (vertex == null) {
      throw new RuntimeException("Vertex cannot be null");
    }
    if (graph.containsKey(vertex)) {
      return;
    }
    graph.put(vertex, new ArrayList<>());
  }

  @Override
  public void addEdge(Edge<T> edge) {
    if (edge == null) {
      throw new RuntimeException("Vertex cannot be null");
    }
    if (!containsVertices(edge)) {
      throw new RuntimeException("Greph does not contain verticies in edge.");
    }
    addVertex(edge.first());
    graph.get(edge.first()).add(edge);
    if (!edge.isDirected()) {
      addVertex(edge.second());
      graph.get(edge.second()).add(edge);
    }
  }

  @Override
  public Generator<T> vertices() {
    return Generators.from(graph.keySet()).generator();
  }

  @Override
  public Generator<Edge<T>> edgesFrom(T vertex) {
    if (vertex == null) {
      throw new RuntimeException("Vertex cannot be null");
    }
    if (!graph.containsKey(vertex)) {
      throw new RuntimeException("Graph does not contain vertex " + vertex);
    }
    return Generators.from(graph.get(vertex)).generator();
  }

  private boolean containsVertices(Edge<T> edge) {
    return graph.containsKey(edge.first()) && graph.containsKey(edge.second());
  }
}
