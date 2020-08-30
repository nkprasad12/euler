package utils.linalg;

public final class Vector {

  private final double[] vector;

  public Vector(double... components) {
    this.vector = components;
  }

  public Vector scale(double k) {
    double[] result = new double[vector.length];
    for (int i = 0; i < vector.length; i++) {
      result[i] = k * vector[i];
    }
    return new Vector(result);
  }

  public Vector plus(Vector other) {
    if (this.vector.length != other.vector.length) {
      throw new RuntimeException("Vectors of different sizes");
    }
    double[] result = new double[vector.length];
    for (int i = 0; i < vector.length; i++) {
      result[i] = this.vector[i] + other.vector[i];
    }
    return new Vector(result);
  }

  public Vector minus(Vector other) {
    if (this.vector.length != other.vector.length) {
      throw new RuntimeException("Vectors of different sizes");
    }
    double[] result = new double[vector.length];
    for (int i = 0; i < vector.length; i++) {
      result[i] = this.vector[i] - other.vector[i];
    }
    return new Vector(result);
  }

  public double dot(Vector other) {
    if (this.vector.length != other.vector.length) {
      throw new RuntimeException("Vectors of different sizes");
    }
    double result = 0d;
    for (int i = 0; i < vector.length; i++) {
      result += (this.vector[i] * other.vector[i]);
    }
    return result;
  }

  public Double component(int i) {
    return vector[i];
  }

  public Double c(int i) {
    return component(i);
  }

  @Override 
  public String toString() {
    String result = "(" + vector[0];
    for (int i = 1; i < vector.length; i++) {
       result += ", " + vector[i];
    }
    return result + ")";
  }
}
