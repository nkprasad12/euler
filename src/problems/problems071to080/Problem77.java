package problems.problems071to080;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import utils.primes.Primes;

public class Problem77 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    Primes primes = new Primes();
    PrimeSumHelper helper = new PrimeSumHelper(primes);
    long number = 10;
    while (true) {
      if (helper.waysToWriteAsSumOfPrimes(number) > 5000) {
        return String.valueOf(number);
      }
      number++;
    }
  }

  /*
    F(number, maxAllowedPrimeInSum) = # of ways to write number as the sum of primes <= maxAllowedPrimeInSum
    F(n, m) = For all prime p <= maxAllowedPrimeInSum, sum F(n - p, p)

    F(n, m) = 1 if n = 0, 0 if n < 0, and recursion above otherwise
    K, F(K, K)
                  p = 7    p = 5     p = 3     p = 2
    F(10, 10) = F(3, 7) + F(5, 5) + F(7, 3) + F(8, 2)
    F(3, 7) = F(0, 3) + F(1, 2)
  */
  private static class PrimeSumHelper {

    Primes primes;
    HashMap<String, Long> table;

    PrimeSumHelper(Primes primes) {
      this.primes = primes;
      table = new HashMap<>();
    }

    long waysToWriteAsSumOfPrimes(long number) {
      return get(number, number);
    }

    private long get(long number, long maxAllowedInSum) {
      if (number < 0) {
        return 0;
      }
      if (number == 0) {
        return 1;
      }
      if (number == 1) {
        return 0;
      }
      if (maxAllowedInSum > number) {
        return get(number, number);
      }
      String key = number + " " + maxAllowedInSum;
      if (!table.containsKey(key)) {
        long ways = 0;
        for (Long prime : primes.primesUpTo(maxAllowedInSum)) {
          ways += get(number - prime, prime);
        }
        table.put(key, ways);
      }
      return table.get(key);
    }
  }
}
