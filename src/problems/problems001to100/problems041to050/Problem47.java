package problems.problems001to100.problems041to050;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem47 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    long i = 1;
    int numConsecutive = 0;
    while (true) {
      if (i % 100 == 0) {
        System.out.println(i);
      }

      if (factor.factorizationOf(i).factorMap().keySet().size() == 4) {
        numConsecutive++;
      } else {
        numConsecutive = 0;
      }

      if (numConsecutive == 4) {
        break;
      }

      i++;
    }

    System.out.println(i - 3);
  }
}
