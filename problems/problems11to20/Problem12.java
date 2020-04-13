package problems.problems11to20;

import java.lang.invoke.MethodHandles;

import utils.primes.PrimeFactorization;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem12 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    long k = 2;
    PrimeFactorization last = factor.of(k - 1);
    PrimeFactorization current = factor.of(k);
    PrimeFactorization next = factor.of(k + 1);
    PrimeFactorization kthTriangle = factor.of(3);
    while (kthTriangle.numberOfDivisors() <= 500) {
      k++;
      last = current;
      current = next;
      next = factor.of(k + 1);
      kthTriangle = kthTriangle.multiplyBy(next).divideBy(last).reduce().numerator();
    }

    System.out.println(kthTriangle.toLong());
  }
}