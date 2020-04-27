package problems.problems011to020;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorization;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem12 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    long k = 2;
    PrimeFactorization last = factor.factorizationOf(k - 1);
    PrimeFactorization current = factor.factorizationOf(k);
    PrimeFactorization next = factor.factorizationOf(k + 1);
    PrimeFactorization kthTriangle = factor.factorizationOf(3);
    while (kthTriangle.numberOfDivisors() <= 500) {
      k++;
      last = current;
      current = next;
      next = factor.factorizationOf(k + 1);
      kthTriangle = kthTriangle.multiplyBy(next).divideBy(last).reduce().numerator();
    }
    return String.valueOf(kthTriangle.toLong());
  }
}
