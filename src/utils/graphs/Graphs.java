package src.utils.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import src.utils.datastructures.DisjointSet;
import src.utils.generators.Generators;

public final class Graphs {

  /** 
   * Given a graph, returns the minimal spanning set of edges. 
   *
   * This is computed using Kruskal's algorithm.
   */
  public static <T> Set<Edge<T>> kruskal(Graph<T> graph) {
    HashSet<Edge<T>> minimumSet = new HashSet<>();
    DisjointSet<T> disjointSet = new DisjointSet<>();

    Generators.from(graph.vertices())
        .forEach(v -> disjointSet.add(v));
    ArrayList<Edge<T>> edges = new ArrayList<>();
    edges.addAll(graph.getEdges());
    Collections.sort(
        edges, 
        (e1, e2) -> 
            Long.compare(e1.weight(), e2.weight()));
    
    for (Edge<T> e : edges) {
      T firstRep = disjointSet.find(e.first());
      T secondRep = disjointSet.find(e.second());
      if (!firstRep.equals(secondRep)) {
        minimumSet.add(e);
        disjointSet.union(firstRep, secondRep);
      }
    }

    return minimumSet;
  }

}