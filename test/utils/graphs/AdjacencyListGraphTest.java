package test.utils.graphs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static test.Assertions.assertGeneratesUnordered;
import static test.Assertions.assertGeneratesNone;

import java.util.Arrays;

import org.junit.Test;

import src.utils.graphs.AdjacencyListGraph;
import src.utils.graphs.Edge;
import src.utils.graphs.Graph;

public class AdjacencyListGraphTest {

  @Test 
  public void addVertex_addsToListOfVertices() {
    Graph<Integer> graph = graph();
    graph.addVertex(9);

    assertGeneratesUnordered(graph.vertices(), 9);
  }

  @Test 
  public void addVertices_addsToListOfVertices() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(4, 7, 6));
    
    assertGeneratesUnordered(graph.vertices(), 4, 6, 7);
  }

  @Test 
  public void addEdge_withoutVertexInEdge_throws() {
    Graph<Integer> graph = graph();
    Edge<Integer> edge = Edge.directed(3, 5);

    assertThrows(RuntimeException.class, () -> graph.addEdge(edge));
    graph.addVertex(3);    
    assertThrows(RuntimeException.class, () -> graph.addEdge(edge));
  }

  @Test 
  public void addEdge_verticesInGraph_addsEdge() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5));

    Edge<Integer> edge = Edge.undirected(3, 5);
    graph.addEdge(edge);

    assertTrue(graph.getEdges().contains(edge));
  }

  @Test 
  public void addEdges_verticesInGraph_addsEdges() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5, 7));

    Edge<Integer> edgeA = Edge.undirected(3, 5);
    Edge<Integer> edgeB = Edge.undirected(3, 7);
    graph.addEdges(Arrays.asList(edgeA, edgeB));

    assertTrue(graph.getEdges().contains(edgeA));
    assertTrue(graph.getEdges().contains(edgeB));
  }

  @Test 
  public void edgesFrom_undirected_addsToBoth() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5));

    Edge<Integer> edge = Edge.undirected(3, 5);
    graph.addEdge(edge);

    assertTrue(graph.getEdgesFrom(3).contains(edge));
    assertTrue(graph.getEdgesFrom(5).contains(edge));
  }

  @Test 
  public void edgesFrom_directed_addsToFirst() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5));

    Edge<Integer> edge = Edge.directed(3, 5);
    graph.addEdge(edge);

    assertTrue(graph.getEdgesFrom(3).contains(edge));
    assertFalse(graph.getEdgesFrom(5).contains(edge));
  }

  @Test 
  public void neighborsOf_undirected_addsToBoth() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5));

    Edge<Integer> edge = Edge.undirected(3, 5);
    graph.addEdge(edge);

    assertGeneratesUnordered(graph.neighborsOf(3), 5);
    assertGeneratesUnordered(graph.neighborsOf(5), 3);
  }

  @Test 
  public void neighborsOf_directed_addsToFirst() {
    Graph<Integer> graph = graph();
    graph.addVertices(Arrays.asList(3, 5));

    Edge<Integer> edge = Edge.directed(3, 5);
    graph.addEdge(edge);

    assertGeneratesUnordered(graph.neighborsOf(3), 5);
    assertGeneratesNone(graph.neighborsOf(5));
  }

  public static <T> Graph<T> graph() {
    return new AdjacencyListGraph<T>();
  }
}