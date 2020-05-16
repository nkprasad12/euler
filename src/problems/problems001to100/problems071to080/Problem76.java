package problems.problems001to100.problems071to080;

import java.lang.invoke.MethodHandles;

public class Problem76 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  /*
    F(number, maxAllowed) = # of ways to write number as the sum of integers <= maxAllowed
    F(n, m) = For all integers k <= m, sum F(n - k, k)

    F(n, m) = 1 if n = 0, 0 if n < 0, and recursion above otherwise
  */
  public static String solution() {
    long N = 100l;
    return String.valueOf(numSums(N, N - 1, new Long[(int) N + 1][(int) N]));
  }

  public static long numSums(long n, long k, Long[][] memo) {
    if (k > n) {
      return numSums(n, n, memo);
    }
    if (n == 0) {
      return 1;
    }
    if (k == 1) {
      return 1;
    }
    if (memo[(int) n][(int) k] != null) {
      return memo[(int) n][(int) k];
    }
    long result = 0;
    for (long m = 1; m <= k; m++) {
      long num = numSums(n - m, m, memo);
      result += num;
    }
    memo[(int) n][(int) k] = result;
    return result;
  }
}
