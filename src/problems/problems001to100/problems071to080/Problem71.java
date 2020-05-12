package problems.problems001to100.problems071to080;

import java.lang.invoke.MethodHandles;

import utils.numbers.NumericUtils;

public class Problem71 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int maxN = Integer.MIN_VALUE;
    int maxD = -6;
    double diff = 100000000d;
    for (int d = 2; d <= 1000000; d++) {
      if (d == 7) {
        continue;
      }
      int x = (3 * d) / 7;
      if (3 * d - 7 * x == 0) {
        continue;
      }
      double xDiff = 3.0d / 7.0d - (x * 1.0d / d);
      if (xDiff < diff) {
        maxN = x;
        diff = xDiff;
        maxD = d;
      }
    }
    return Long.toString(maxN / NumericUtils.gcd(maxN, maxD));
  }
}