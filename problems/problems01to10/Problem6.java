package problems.problems01to10;

import java.lang.invoke.MethodHandles;
import utils.numbers.Series;

public class Problem6 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(sumSquareDifference(100));
  }

  static long sumSquareDifference(int n) {
    long sumOfIntegers = Series.ofIntegersUpTo(n);
    return sumOfIntegers * sumOfIntegers - Series.ofIntegerSquaresUpTo(n);
  }
}