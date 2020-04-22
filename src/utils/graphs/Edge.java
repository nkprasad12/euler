package src.utils.graphs;

/** Represents an edge of a graph. */
public final class Edge<T> {

  public static <T> Edge<T> directed(T from, T to, long weight) {
    return new Edge<>(from, to, weight, true);
  }

  public static <T> Edge<T> directed(T from, T to) {
    return directed(from, to, 1);
  }

  public static <T> Edge<T> undirected(T from, T to, long weight) {
    return new Edge<>(from, to, weight, false);
  }

  public static <T> Edge<T> undirected(T from, T to) {
    return undirected(from, to, 1);
  }

  private final T first;
  private final T second;
  private final long weight;
  private final boolean isDirected;

  private Edge(T first, T second, long weight, boolean isDirected) {
    if (first == null || second == null) {
      throw new RuntimeException("Edge from / to cannot be null");
    }
    this.first = first;
    this.second = second;
    this.weight = weight;
    this.isDirected = isDirected;
  }

  public T first() {
    return first;
  }

  public T second() {
    return second;
  }

  public long weight() {
    return weight;
  }

  public boolean isDirected() {
    return isDirected;
  }

  @Override
  public String toString() {
    String result = "";
    result += isDirected ? "d" : "u";
    result += "(" + first + ", " + second + ", w=" + weight + ")";
    return result;
  }
}