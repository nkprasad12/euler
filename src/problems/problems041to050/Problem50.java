package src.problems.problems041to050;

import static src.utils.generators.Generators.from;
import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.List;
import src.utils.generators.base.tuples.Tuples;
import src.utils.primes.Primes;

public class Problem50 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Primes primeHelper = new Primes();
    List<Long> primes = primeHelper.primesUpTo(1000000);
    List<Long> primeSums =
        from(primes)
            .reducing(0l, (sum, p) -> sum + p)
            .whileTrue(sum -> sum < Long.MAX_VALUE - 1000000)
            .list();

    range(1, primeSums.size() - 1)
        .mapAndPair(i -> range(0, i - 1))
        .mapPair((i, j) -> Tuples.pair(i - j, primeSums.get(i) - primeSums.get(j)))
        .filter(pair -> pair.second() < 1000000)
        .filter(pair -> primeHelper.isPrime(pair.second()))
        .max(pair -> (long) pair.first().intValue())
        .print();

    int totalPrimesRemoved = 0;
    long maxCon = 0;
    long maxSum = 0;
    long startingPoint = 0;
    int primesProcessed = 0;
    for (Long p : primes) {
      if (p > 1000000) {
        continue;
      }
      for (int j = 0; j < primeSums.size(); j++) {
        long sum = primeSums.get(j);
        if (sum - totalPrimesRemoved > 1000000) {
          break;
        }

        if (primeHelper.isPrime(sum - totalPrimesRemoved) && j - primesProcessed > maxCon) {
          maxCon = j - primesProcessed;
          maxSum = sum - totalPrimesRemoved;
          startingPoint = p;
        }
      }
      totalPrimesRemoved += p;
      primesProcessed++;
    }
    System.out.println(maxCon);
    System.out.println(maxSum);
    System.out.println(startingPoint);

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
