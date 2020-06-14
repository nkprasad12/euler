package problems.problems001to100.problems071to080;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

public class Problem78 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    HashMap<String, Long> memo = new HashMap<String, Long>();
    long n = 1;
    while (p(n, n, memo) != 0 && n < 100) {
      // System.out.println("n, p(n): " + n + ", " + p(n, n, memo));
      n++;
    }
    return Long.toString(n);
  }

  public static long p(long n, long k, HashMap<String, Long> memo) {
    if (k > n) {
      return p(n, n, memo);
    }
    if (n == 0) {
      return 1l;
    }
    if (k == 1) {
      return 1l;
    }
    String key = Long.toString(n) + "_" + Long.toString(k);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    long result = 0;
    for (long m = 1; m <= k; m++) {
      // p(n, k) = p(n - 1, 1) + p(n - 2, 2) + ... + p(0, k)
      long num = p(n - m, m, memo);
      result = (result + num) % 1000000l;
    }
    memo.put(key, result);
    return result;
  }

  // Computation using Generalized Pentagonals
  // PN_n = (3n^2 - n) / 2
  // Generalized pentagonals = PN_0, PN_1, PN_(-1), PN_2, PN(-2), PN_3, PN_(-3), ...
  // p(n) =
  //        0, if n < 0
  //        Sum of (-1)^(k - 1) p(n - PN_k)
  //        for all k != 0.
}
