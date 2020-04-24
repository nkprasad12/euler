package src.utils.graphs;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import src.utils.generators.Generator;
import src.utils.generators.Generators;

/** Basic representation of a graph. */
public interface Graph<T> {

  /**
   * Adds the given vertex to the graph.
   *
   * <p>Must not be null. A vertex is added in a state unconnected to any other vertices.
   */
  void addVertex(T vertex);

  /** Adds all the given vertices to the graph. */
  default void addVertices(Collection<T> vertices) {
    for (T vertex : vertices) {
      addVertex(vertex);
    }
  }

  /**
   * Adds the given edge to the graph.
   *
   * @param edge, must not be null. The vertices of the edge must have been added previously to the
   *     graph.
   */
  void addEdge(Edge<T> edge);

  /** Adds all the given edges to the graph. */
  default void addEdges(Collection<Edge<T>> edges) {
    for (Edge<T> edge : edges) {
      addEdge(edge);
    }
  }

  /**
   * Generates all the edges that start from the given vertex.
   *
   * <p>Throws if the vertex is not in the graph.
   */
  Generator<Edge<T>> edgesFrom(T vertex);

  /** A list of all edges that start from the given vertex. */
  default List<Edge<T>> getEdgesFrom(T vertex) {
    return Generators.from(edgesFrom(vertex)).list();
  }

  /**
   * Generates all the neighbors of a given vertex.
   *
   * <p>If there is a directed edge (u, v) in the graph, neighborsOf(u) will generate v, but not
   * vice versa.
   *
   * <p>Throws if the given vertex is not in the Graph.
   */
  default Generator<T> neighborsOf(T vertex) {
    return Generators.from(edgesFrom(vertex))
        .map(edge -> edge.first().equals(vertex) ? edge.second() : edge.first())
        .generator();
  }

  /** A list of all the neighbors of a given vertex. */
  default List<T> getNeighborsOf(T vertex) {
    return Generators.from(neighborsOf(vertex)).list();
  }

  /** Generates all the vertices in the graph. */
  Generator<T> vertices();

  /** A list of all the vertices in the graph. */
  default List<T> getVertices() {
    return Generators.from(vertices()).list();
  }

  /** A set of all the edges in the graph. */
  default Set<Edge<T>> getEdges() {
    return Generators.from(vertices()).flatMap(v -> Generators.from(edgesFrom(v))).set();
  }
}
