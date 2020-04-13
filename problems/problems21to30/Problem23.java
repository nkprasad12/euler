package problems.problems21to30;

import java.lang.invoke.MethodHandles;
import java.util.Set;

import utils.generators.Generators;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem23 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);  

    long upperBound = 28123;
    Set<Long> abundants = Generators.naturalsUpTo(upperBound).filter(factor::isAbundant).set();
    Long result =
        Generators.naturalsUpTo(upperBound)
            .filter(
                i -> 
                    !Generators.from(abundants)
                        .anyMatch(abundantNum -> abundants.contains(i - abundantNum)))
            .reduce(0l, (a, b) -> a + b);
    System.out.println(result);
  }
}