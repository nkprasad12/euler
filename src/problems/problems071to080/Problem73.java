package problems.problems071to080;

import java.lang.invoke.MethodHandles;

import utils.numbers.NumericUtils;

public class Problem73 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int numberOfFractions = 0;
    for (int d = 4; d <= 12000; d++) {
      for (int j = (int) Math.ceil(d / 3.0); j <= d / 2; j++) {
        if (NumericUtils.gcd(d, j) == 1) {
          numberOfFractions++;
        }
      }        
    }
    return String.valueOf(numberOfFractions);
  }
}
