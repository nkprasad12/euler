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
    primes.primesUpTo(1000000);
    Memo memo = new Memo(primes);
    long k = 10;
    while (true) {
      if (memo.get(k, k) > 5000) {
        return String.valueOf(k);
      }
      k++;
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

  private static class Memo {

    Primes primes;
    HashMap<String, Long> table;
    
    Memo(Primes primes) {
      this.primes = primes;
      table = new HashMap<>();
    }

    long get(long n, long m) {
      if (n < 0) {
        return 0;
      }
      if (n == 0) {
        return 1;
      }
      if (n == 1) {
        return 0;
      }
      if (m > n) {
        return get(n, n);
      }
      String key = n + " " + m;
      if (!table.containsKey(key)) {
        long ways = 0;
        for (Long p : primes.primesUpTo(m)) {
          ways += get(n - p, p);
        }
        table.put(key, ways);
      }
      return table.get(key);
    }
  }
}
