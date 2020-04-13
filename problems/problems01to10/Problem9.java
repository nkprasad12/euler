package problems.problems01to10;

import java.lang.Math;
import java.lang.invoke.MethodHandles;

public class Problem9 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    // See comments below
    long k = -1;
    long root = -1;
    for (long i = 0; i < 1000; i++) {
      long sum = 2000000l + i * i;
      root = (long) Math.sqrt(sum);
      if (sum == root * root) {
        k = i;
        break;
      }
    }
    long a = ((2000 - k) + root) / 2;
    if (a < 1 || a > 333) {
      a = ((2000 - k) - root) / 2;
    }
    long b = a + k;
    long c = (long) Math.sqrt(a * a + b * b);
    System.out.println(String.format("a: %d, b: %d, c: %d, abc: %d", a, b, c, a * b * c));
  }

  /**
   * Find abc for a, b, c where
   * a < b < c and a^2 + b^2 = c^2 
   * and a + b + c = 1000
   *
   * 1000 - (a + b) = c
   *
   * (1000 - (a + b))^2 = c^2 = a^2 + b^2
   * 1,000,000 - 2,000(a + b) + (a + b)^2 = a^2 + b^2
   * 1,000,000 - 2,000a - 2,000b + 2ab = 0
   * 
   * Let b = a + k
   *
   * a(a + k) = 1000(2a + k) - 500000
   * a = (2000 - k) / 2 +/- sqrt(k^2 + 2000000) / 2
   * 
   * a must be an integer, so we are looking for numbers k
   * such that sqrt(k^2 + 2000000) is an integer.
   * We know k < 1000, so we can simply try values.
   */


}