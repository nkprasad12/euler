package src.problems.problems021to030;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import src.utils.generators.Generators;
import src.utils.primes.PrimeFactorizations;
import src.utils.primes.Primes;

public class Problem21 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    ArrayList<Long> sumOfDivisors =
        // Based on heuristics, no number < 10000 has a sum of divisors > 30000.
        // Confirmed by empirical evidence
        Generators.range(1l, 30000l)
            .map(n -> factor.properDivisorsOf(n))
            .map(list -> Generators.from(list).reduce(0l, (a, b) -> a + b))
            .collectInto(new ArrayList<>());
    sumOfDivisors.add(0, 0l);
    Generators.range(1l, 9999l)
        .filter(n -> isAmicable(sumOfDivisors, n))
        .reducing(0l, (a, b) -> a + b)
        .lastValue()
        .print();
  }

  static boolean isAmicable(ArrayList<Long> sumOfDivisors, long n) {
    long sumOfDivisorsOfN = sumOfDivisors.get((int) n);
    long sumOfDivisorsOfSum = sumOfDivisors.get((int) sumOfDivisorsOfN);
    return n != sumOfDivisorsOfN && n == sumOfDivisorsOfSum;
  }
}
