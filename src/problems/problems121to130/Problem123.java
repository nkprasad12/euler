package problems.problems121to130;

import java.lang.invoke.MethodHandles;
import java.util.List;

import utils.primes.Primes;

public class Problem123 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  static String solution() {
    /* 
     * Let p be the nth prime. We are searching for the values 
     * (p - 1)^n + (p + 1)^n mod p^2
     *
     * Noting that (a + b)^n = Sum from k = 0 to n of C(n, k) a^k b^(n-k),
     * The above expression is equal to: 
     * n odd: Sum from odd k = 1 to n of 2 C(n, k) p^k (mod p^2)
     * n even: Sum from even k = 0 to n of 2 C(n, k) p^k (mod p^2)
     *
     * Note that in both cases, all terms after the first are equivalent to 0 (mod p^2), so
     * n odd: 2 n p (mod p^2)
     * n even: 2 (mod p^2)
     *
     * Since p > 2n for n >= 5, we simply need to find the first odd value of n for which
     * 2 * n * p > 10^10. 
     *
     * For all n > 6, we have that the nth prime p is in bounds
     * n (log n + log log n - 1) <= p <= n (log n + log log n), so
     * n log n < p < 2 n log n
     * 2 n log10 n < p < 5 n log10 n
     * 4 n^2 log10 n < 2 n p < 10 n^2 log10 n
     *
     * Therefore, we see if 
     * U(n) = 10 n^2 log10 n > 2 n p > 10^10, 
     * U(10000) = 4 * 10^9, so U(15000) > 10^10.
     * 
     * 5 n log10 n > p, so 5 * 15000 log10 15000 = 5 * 15000 * (4 + log10 15) > p. 
     * This means p < 320000. 
     * A similar analysis for lower bounds yields
     * 80000 < p < 320000
     */
     Primes primeHelper = new Primes();
     List<Long> primes = primeHelper.primesUpTo(320000);
     for (int n = 1; n < primes.size(); n += 2) {
      if (primes.get(n - 1) < 80000) {
        continue;
      }
      // 2 n p > 10^10
      if (primes.get(n - 1) * ((long) n) > 5000000000l) {
        return String.valueOf(n);
      }
     }
     return "Your math is bad and you should feel bad";
  }

}