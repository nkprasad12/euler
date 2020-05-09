package problems.problems001to100.problems081to090;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;
import utils.primes.Primes;

public class Problem87 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    Primes primeHelper = new Primes();
    List<Long> primes = primeHelper.primesUpTo(8072);
    HashSet<Long> num = new HashSet<>();
    for (int i = 0; i < 152; i++) {
      long p4 = primes.get(i) * primes.get(i) * primes.get(i) * primes.get(i);
      for (int j = 0; j < 569; j++) {
        long p3 = primes.get(j) * primes.get(j) * primes.get(j);
        for (int k = 0; k < 8071; k++) {
          long p2 = primes.get(k) * primes.get(k);
          if (p4 + p3 + p2 < 50000000l) {
            num.add(p4 + p3 + p2);
          } else {
            break;
          }
        }
      }
    }
    return String.valueOf(num.size());
  }
}
