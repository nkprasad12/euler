package problems.problems001to100.problems061to070;

import java.lang.invoke.MethodHandles;

public class Problem63 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    // Integers in range [nth root of 10^(n-1), 9]
    // nth root of 10^(n-1) = nth root of (10^n / 10) = 10 / nth root of 10
    long total = 0;
    for (int n = 1; n <= 21; n++) {
      long lower = (long) Math.ceil(10 / Math.pow(10, 1.0 / n));
      total += 10 - lower;
    }
    return String.valueOf(total);
  }
}
