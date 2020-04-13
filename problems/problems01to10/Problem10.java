package problems.problems01to10;

import java.lang.invoke.MethodHandles;
import java.util.SortedSet;

import utils.primes.Primes;

public class Problem10 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    SortedSet<Long> primesUpTo2Mill = primes.primesUpTo(2000000l);
    long sum = 0;
    for (Long prime : primesUpTo2Mill) {
      sum += prime;
    }
    System.out.println(sum);
  }
}