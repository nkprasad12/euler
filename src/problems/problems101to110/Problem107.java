package problems.problems101to110;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import utils.generators.Generators;
import utils.graphs.AdjacencyListGraph;
import utils.graphs.Edge;
import utils.graphs.Graph;
import utils.graphs.Graphs;

public class Problem107 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertices(Generators.range(0, 39).list());
    Generators.fromTextFile("problem107.txt")
        .addIndices()
        .mapPair(u -> u, line -> Arrays.asList(line.split(",")))
        .mapPair(
            (u, list) ->
                Generators.from(list)
                    .addIndices()
                    .filter((v, str) -> v > u)
                    .filter((v, str) -> !str.equals("-"))
                    .mapPair((v, str) -> Edge.undirected(u, v, Long.parseLong(str))))
        .flatMap(gen -> gen)
        .forEach(edge -> graph.addEdge(edge));
    long totalWeight =
        Generators.from(graph.getEdges()).reduce(0l, (sum, edge) -> sum + edge.weight());
    long minWeight =
        Generators.from(Graphs.kruskal(graph)).reduce(0l, (sum, edge) -> sum + edge.weight());
    System.out.println("Savings: " + (totalWeight - minWeight));

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
