package src.problems.problems001to010;

import java.lang.invoke.MethodHandles;
import src.utils.primes.PrimeFactorization;
import src.utils.primes.PrimeFactorizations;
import src.utils.primes.Primes;

public class Problem5 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    PrimeFactorization allFactors = factor.factorizationOf(1);
    for (long i = 2; i <= 20; i++) {
      allFactors =
          allFactors.multiplyBy(
              allFactors.divideBy(factor.factorizationOf(i)).reduce().denominator());
    }
    System.out.println(allFactors.toLong());
  }
}
