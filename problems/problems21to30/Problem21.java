package problems.problems21to30;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

import java.util.ArrayList;

public class Problem21 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    ArrayList<Long> sumOfDivisors = 
        Generators.range(1l, 30000l) // Based on heuristics, no number < 10000 has a sum of divisors > 30000.
                                     // Confirmed by empirical evidence
            .map(n -> factor.properDivisorsOf(n))
            .map(list -> Generators.from(list)
            .reduce(0l, (a, b) -> a + b))
            .collectInto(new ArrayList<>());
    sumOfDivisors.add(0, 0l);
    Generators.range(1l, 9999l)
        .filter(n -> isAmicable(sumOfDivisors, n))
        .reducing(0l, (a, b) -> a + b)
        .printLast();
  }

  static boolean isAmicable(ArrayList<Long> sumOfDivisors, long n) {
    long sumOfDivisorsOfN = sumOfDivisors.get((int) n);
    long sumOfDivisorsOfSum = sumOfDivisors.get((int) sumOfDivisorsOfN);
    return n != sumOfDivisorsOfN && n == sumOfDivisorsOfSum; 
  }
}