package src.problems.problems021to030;

import java.lang.invoke.MethodHandles;
import java.util.Set;
import src.utils.generators.Generators;
import src.utils.primes.PrimeFactorizations;
import src.utils.primes.Primes;

public class Problem23 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    long upperBound = 28123;
    Set<Long> abundants = Generators.range(1, upperBound).filter(factor::isAbundant).set();
    Long result =
        Generators.range(1, upperBound)
            .filter(
                i ->
                    !Generators.from(abundants)
                        .anyMatch(abundantNum -> abundants.contains(i - abundantNum)))
            .reduce(0l, (a, b) -> a + b);
    System.out.println(result);
  }
}
