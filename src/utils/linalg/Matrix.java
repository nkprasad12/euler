package utils.linalg;

public final class Matrix {
  
  public static Matrix from(long[][] matrix) {
    return new Matrix(matrix);
  }

  private long[][] matrix;

  private Matrix(long[][] matrix) {
    this.matrix = matrix;
  }

  public long[][] getArray() {
    return matrix;
  }

  public Matrix multiplyBy(Matrix other) {
    if (matrix[0].length != other.matrix.length) {
      throw new RuntimeException("Invalid matrix multiplication.");
    }

    long[][] result = new long[matrix.length][other.matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < other.matrix[0].length; j++) {
        for (int k = 0; k < matrix[0].length; k++) {
          result[i][j] += matrix[i][k] * other.matrix[k][j];
        }
      }
    }
    return new Matrix(result);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    Matrix m = (Matrix) o;
    if (matrix.length != m.matrix.length) {
      return false;
    }
    if (matrix[0].length != m.matrix[0].length) {
      return false;
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] != m.matrix[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String toString() {
    String result = "\n";
    for (long[] row : matrix) {
      for (long num : row) {
        result += num + " ";
      }
      result += "\n";
    }
    result += "\n";
    return result;
  } 
}