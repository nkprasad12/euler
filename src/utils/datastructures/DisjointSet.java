package src.utils.datastructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * Implementation of a Disjoint Set data structure.
 * 
 * Uses path compression and union by rank to provide near constant time
 * amortizaed cost for operations.
 */
public final class DisjointSet<T> {

  private final HashMap<T, Node<T>> nodes;

  public DisjointSet() {
    nodes = new HashMap<>();
  }

  /** Adds the given value to the disjoint set. */
  public void add(T t) {
    if (!nodes.containsKey(t)) {
      nodes.put(t, new Node<>(t));
    }
  }

  /** Adds all the given elements to the disjoint set. */
  @SafeVarargs
  public final void addAll(T ... elements) {
    addAll(Arrays.asList(elements));
  }

  /** Adds all the given elements to the disjoint set. */
  public final void addAll(Collection<T> elements) {
    for (T t : elements) {
      add(t);
    }
  }

  /** Finds the representative value for the given value. */
  public T find(T t) {
    Node<T> tNode = findNode(t);
    return tNode == null ? null : tNode.value;
  }

  /** Declare the given values to be equivalent; i.e, having the same representative. */
  public void union(T a, T b) {
    Node<T> aRoot = findNode(a);
    Node<T> bRoot = findNode(b);
    if (aRoot == bRoot) {
      return;
    }
    if (aRoot.rank < bRoot.rank) {
      Node<T> tmp = aRoot;
      aRoot = bRoot;
      bRoot = tmp;
    }
    bRoot.parent = aRoot;
    aRoot.rank += aRoot.rank == bRoot.rank ? 1 : 0;
  }

  private Node<T> findNode(T t) {
    if (!nodes.containsKey(t)) {
      return null;
    }
    Node<T> root = nodes.get(t);
    while (root.parent != null) {
      root = root.parent;
    }
    Node<T> current = nodes.get(t);
    while (current.parent != null) {
      Node<T> parent = current.parent;
      current.parent = root;
      current = parent;
    }
    return root;
  }

  private static final class Node<T> {
  
    private final T value;
    private Node<T> parent;
    private int rank;

    private Node(T t) {
      if (t == null) {
        throw new RuntimeException("Trying to create node with null value.");
      }
      this.value = t;
      this.parent = null;
      this.rank = 0;
    }
  }
}