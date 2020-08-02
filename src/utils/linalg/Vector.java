package utils.linalg;

import java.util.ArrayList;
import java.util.List;

public final class Vector {

  private final List<Double> vector;

  public Vector(List<Double> vector) {
    this.vector = vector;
  }

  public Vector scale(Double k) {
    ArrayList<Double> result = new ArrayList<Double>(vector.size());
    for (int i = 0; i < vector.size(); i++) {
      result.add(k * this.vector.get(i));
    }
    return new Vector(result);
  }

  public Vector plus(Vector other) {
    if (this.vector.size() != other.vector.size()) {
      throw new RuntimeException("Vectors of different sizes");
    }
    ArrayList<Double> result = new ArrayList<Double>(vector.size());
    for (int i = 0; i < vector.size(); i++) {
      result.add(this.vector.get(i) + other.vector.get(i));
    }
    return new Vector(result);
  }

  public Vector minus(Vector other) {
    if (this.vector.size() != other.vector.size()) {
      throw new RuntimeException("Vectors of different sizes");
    }
    ArrayList<Double> result = new ArrayList<Double>(vector.size());
    for (int i = 0; i < vector.size(); i++) {
      result.add(this.vector.get(i) - other.vector.get(i));
    }
    return new Vector(result);
  }

  public Double dot(Vector other) {
    if (this.vector.size() != other.vector.size()) {
      throw new RuntimeException("Vectors of different sizes");
    }
    Double result = 0d;
    for (int i = 0; i < vector.size(); i++) {
      result += (this.vector.get(i) * other.vector.get(i));
    }
    return result;
  }

  public Double component(int i) {
    return vector.get(i);
  }
}
