package src.utils.graphs;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

  Generator<Edge<T>> edgesFrom(T vertex);

  default List<Edge<T>> getEdgesFrom(T vertex) {
    return Generators.from(edgesFrom(vertex)).list();
  }

  default Generator<T> neighborsOf(T vertex) {
    return Generators.from(edgesFrom(vertex))
        .map(
            edge ->
                edge.first().equals(vertex) 
                    ? edge.second() : edge.first())
        .generator();
  }

  default List<T> getNeighborsOf(T vertex) {
    return Generators.from(neighborsOf(vertex)).list();
  }

  Generator<T> vertices();

  default List<T> getVertices() {
    return Generators.from(vertices()).list();
  }

  default Set<Edge<T>> getEdges() {
    return Generators.from(vertices())
        .flatMap(v -> Generators.from(edgesFrom(v)))
        .set();
  }
}