package src.problems.problems011to020;

import java.lang.invoke.MethodHandles;

public class Problem15 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(latticePaths(20));
  }

  // Computes the number of lattice paths through an n x n grid.
  static long latticePaths(int n) {
    long[][] grid = new long[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        if (i == 0 || j == 0) {
          grid[i][j] = 1;
        } else {
          grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
        }
      }
    }
    return grid[n][n];
  }
}