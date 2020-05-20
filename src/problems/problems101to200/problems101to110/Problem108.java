package problems.problems101to200.problems101to110;

import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import utils.numbers.BigNumber;
import utils.primes.Primes;

public class Problem108 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return diophantineReiprocals(1000);
  }

  /*
   Note that if 1 / n = 1 / x + 1 / y, xy = ny + nx, so if n and x are fixed, then the original
   equation has a solution whenever y = xn / (x - n) is an integer.

   WLOG, we can suppose that x <= y, so the maximum possible value for x is 2n
   (since 1 / 2n + 1 / 2n = 1 /n). Additionally, x > n, since otherwise we have
   1 / x > 1 / n, so 1 / y = 1 / n - 1 / x < 0, but y must be a position integer.

   Therefore, x must be in the interval [n + 1, 2n], so if we define x = n + k, k in [1, n]
   then there's a solution whenever y = (n + k)n / ((n + k) - k) is an integer, or when
   k divides n^2 + nk.

   This is true iff k divides n^2; now, note that for a given divisor d <= n of n^2, there is
   a divisor d' >= n (d' = n^2 / d). Note that for d = n, d' = d = n, so if n^2 has D divisors,
   then (D + 1) / 2 of them are less than or equal to n.

   Therefore, if D(m) is the number of divisors of m, the number of solutions S(n) for a given n
   is (D(n^2) + 1) / 2. Given the prime factorization n = p1^e1 * ... * pt^et, note that
   D(n^2) = (2 * p1 + 1) * ... * (2 * p2 + 1) = 2 * S(n) - 1
  */
  public static String diophantineReiprocals(long N) {
    int k = primesNeeded(N);
    List<Long> primes = firstPrimes(k);
    double[] logPrimes = new double[primes.size()];
    int[] exps = new int[primes.size()];
    double optimal = 0;
    for (int i = 0; i < logPrimes.length; i++) {
      logPrimes[i] = Math.log(primes.get(i));
      optimal += logPrimes[i];
      exps[i] = 1;
    }
    int[] optimalExps = Arrays.copyOf(exps, exps.length);
    long S = 2 * N - 1;
    long L = findMaxPowerRequired(primes);

    int ptr = 0;
    boolean reset = false;
    double[] logMSoFar = new double[primes.size()];
    long[] solutionsSoFar = new long[primes.size()];
    while (true) {
      if (reset) {
        if (ptr == 0) {
          if (exps[0] == L) {
            break;
          } else {
            exps[0]++;
          }
        } else if (exps[ptr] < exps[ptr - 1]) {
          exps[ptr]++;
        } else {
          exps[ptr] = 0;
          ptr--;
          continue;
        }
        reset = false;
      }
      logMSoFar[ptr] = exps[ptr] * logPrimes[ptr] + (ptr == 0 ? 0 : logMSoFar[ptr - 1]);
      solutionsSoFar[ptr] = (2 * exps[ptr] + 1) * (ptr == 0 ? 1 : solutionsSoFar[ptr - 1]);
      boolean hasEnoughSolutions = solutionsSoFar[ptr] > S;
      boolean isProductSmaller = logMSoFar[ptr] < optimal;
      if (hasEnoughSolutions || !isProductSmaller || ptr == primes.size() - 1) {
        for (int i = ptr + 1; i < primes.size(); i++) {
          exps[i] = 0;
        }
        reset = true;
        if (hasEnoughSolutions && isProductSmaller) {
          optimal = logMSoFar[ptr];
          optimalExps = Arrays.copyOf(exps, exps.length);
        }
      } else {
        ptr++;
      }
    }

    return expArrayToDigitString(optimalExps, primes);
  }

  static String expArrayToDigitString(int[] exps, List<Long> primes) {
    BigNumber result = fromLong(1);
    for (int i = 0; i < primes.size(); i++) {
      if (exps[i] == 0) {
        break;
      }
      result = result.multiplyBy(fromLong(primes.get(i)).toPower(exps[i]));
    }
    return result.toString();
  }

  static long findMaxPowerRequired(List<Long> primes) {
    long largestPrime = primes.get(primes.size() - 1);
    long twoPower = 1;
    long exponent = 0;
    while (twoPower < largestPrime) {
      twoPower *= 2;
      exponent++;
    }
    return exponent + exponent / 2;
  }

  static int primesNeeded(long solutions) {
    return (int) (Math.log(2 * solutions - 1) / Math.log(3)) + 1;
  }

  static List<Long> firstPrimes(int numPrimes) {
    Primes primes = new Primes();
    long nThPrime = primes.nthPrime(numPrimes);
    return primes.primesUpTo(nThPrime);
  }
}
