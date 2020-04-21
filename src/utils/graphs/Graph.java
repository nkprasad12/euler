package src.utils.graphs;

import java.util.Collection;
import java.util.List;

import src.utils.generators.Generator;
import src.utils.generators.Generators;

public interface Graph<T> {

  void addVertex(T vertex);

  default void addVertices(Collection<T> vertices) {
    for (T vertex : vertices) {
      addVertex(vertex);
    }
  }

  void addEdge(Edge<T> edge);

  default void addEdges(Collection<Edge<T>> edges) {
    for (Edge<T> edge : edges) {
      addEdge(edge);
    }
  }

  Generator<T> neighborsOf(T vertex);

  default List<T> getNeighborsOf(T vertex) {
    return Generators.from(neighborsOf(vertex)).list();
  }

  Generator<T> vertices();

  default List<T> getVertices() {
    return Generators.from(vertices()).list();
  }

  Generator<Edge<T>> edges();

  default List<Edge<T>> getEdges() {
    return Generators.from(edges()).list();
  }
}