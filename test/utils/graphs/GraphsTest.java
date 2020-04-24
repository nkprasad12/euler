package test.utils.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import src.utils.graphs.AdjacencyListGraph;
import src.utils.graphs.Edge;
import src.utils.graphs.Graph;
import src.utils.graphs.Graphs;

public class GraphsTest {

  @Test
  public void kruskal_returnsMinimalSpanningSet() {
    Graph<String> graph = new AdjacencyListGraph<>();
    graph.addVertices(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
    List<Edge<String>> edges =
        Arrays.asList(
            Edge.undirected("A", "B", 7),
            Edge.undirected("A", "D", 5),
            Edge.undirected("B", "D", 9),
            Edge.undirected("B", "C", 8),
            Edge.undirected("B", "E", 7),
            Edge.undirected("C", "E", 5),
            Edge.undirected("D", "E", 15),
            Edge.undirected("D", "F", 6),
            Edge.undirected("E", "F", 8),
            Edge.undirected("E", "G", 9),
            Edge.undirected("F", "G", 11));
    graph.addEdges(edges);

    Set<Edge<String>> minimalSet = Graphs.kruskal(graph);
    assertEquals(minimalSet.size(), 6);
    assertTrue(minimalSet.contains(edges.get(0)));
    assertTrue(minimalSet.contains(edges.get(1)));
    assertTrue(minimalSet.contains(edges.get(4)));
    assertTrue(minimalSet.contains(edges.get(5)));
    assertTrue(minimalSet.contains(edges.get(7)));
    assertTrue(minimalSet.contains(edges.get(9)));
  }
}
