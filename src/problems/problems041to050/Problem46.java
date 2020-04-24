package src.problems.problems041to050;

import static src.utils.generators.Generators.from;
import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.List;
import src.utils.primes.Primes;

public class Problem46 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Primes primesHelper = new Primes();
    primesHelper.primesUpTo(1000000);
    List<Long> doubleSquares = range(1l, 1000l).map(k -> 2 * k * k).list();

    for (long k = 3; k < 1000000; k += 2) {
      if (primesHelper.isPrime(k)) {
        continue;
      }
      final long composite = k;
      boolean canBeWritten =
          from(doubleSquares)
              .whileTrue(sq -> composite > sq + 1)
              .map(sq -> composite - sq)
              .filter(primesHelper::isPrime)
              .anyMatch(p -> true);
      if (!canBeWritten) {
        System.out.println(k);
        break;
      }
    }

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
