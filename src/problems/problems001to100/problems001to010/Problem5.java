package problems.problems001to100.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorization;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem5 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    PrimeFactorization allFactors = factor.factorizationOf(1);
    for (long i = 2; i <= 20; i++) {
      allFactors =
          allFactors.multiplyBy(
              allFactors.divideBy(factor.factorizationOf(i)).reduce().denominator());
    }
    return String.valueOf(allFactors.toLong());
  }
}
