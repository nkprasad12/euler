package problems.problems101to200.problems121to130;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

import utils.generators.Generators;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem124 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  // TODO: Do this more efficiently. Currently this takes ~2 seconds.
  // Note that the maximum radical below 100000 is 2 * 3 * 5 * 7 * 11 * 13
  // Thus, there are only 2^6 distinct values for radicals below 100000.
  // We can instead just find the number of values that have a particular
  // radical and return the radical that brings us over 10000. This should
  // be at least 1 order of magnitude faster.
  public static String solution() {
    Primes primeHelper = new Primes();
    PrimeFactorizations pf = new PrimeFactorizations(primeHelper);

    int k = 10000;
    long N = 100000;
    List<Long> firstN = Generators.range(1, N).list();
    Collections.sort(
        firstN, 
        (a, b) ->
            Long.compare(
                pf.factorizationOf(a).radical(), pf.factorizationOf(b).radical()));
    return firstN.get(k - 1).toString();
  }
}