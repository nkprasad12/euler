package src.problems.problems001to010;

import java.lang.invoke.MethodHandles;
import src.utils.numbers.Series;

public class Problem6 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(sumSquareDifference(100));
  }

  static long sumSquareDifference(int n) {
    long sumOfIntegers = Series.ofIntegersUpTo(n);
    return sumOfIntegers * sumOfIntegers - Series.ofIntegerSquaresUpTo(n);
  }
}