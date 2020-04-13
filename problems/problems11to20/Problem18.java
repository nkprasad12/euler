package problems.problems11to20;

import java.lang.invoke.MethodHandles;
import java.lang.Math;
import java.util.ArrayList;

import utils.generators.Generators;
import utils.numbers.NumericUtils;
import utils.numbers.Series;

public class Problem18 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(maxTrianglePath("problem18.txt"));
  }

  public static int maxTrianglePath(String fileName) {
    ArrayList<Integer> triangle =
        Generators.fromTextFile(fileName)
            .map(Integer::parseInt)
            .collectInto(new ArrayList<>());
    int n = NumericUtils.inverseTriangle(triangle.size());
    for (int row = n - 2; row >= 0; row--) {
      for (int col = 0; col <= row; col++) {
        int i = index(row, col);
        int l = index(row + 1, col);
        int r = index(row + 1, col + 1);
        int value = triangle.get(i);
        triangle.set(i, value + Math.max(triangle.get(l), triangle.get(r)));
      }
    }
    return triangle.get(0);
  }

  static int index(int triangleRow, int triangleCol) {
    return getTriangular(triangleRow + 1) - 1 - (triangleRow - triangleCol);
  }

  static int getTriangular(int k) {
    return (int) Series.ofIntegersUpTo(k);
  }
}