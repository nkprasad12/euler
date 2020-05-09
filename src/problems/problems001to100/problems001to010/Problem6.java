package problems.problems001to100.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.numbers.Series;

public class Problem6 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    /* Sum of first n integers is 1/2 n (n + 1)
     * Sum of first n squares of integers is 1/6  n (n + 1) (2n + 1)
     *
     * Taking the square of the first and subtracting the second gives
     * 1/12 (n-1) n (n + 1) (3n + 2)
     *
     * Plugging in n = 100 gives the following
     */
    System.out.println((99 * 100 * 101 * 302) / 12);
  }

  public static String solution() {
    // Naive solution.
    return String.valueOf(sumSquareDifference(100));
  }

  static long sumSquareDifference(int n) {
    long sumOfIntegers = Series.ofIntegersUpTo(n);
    return sumOfIntegers * sumOfIntegers - Series.ofIntegerSquaresUpTo(n);
  }
}
