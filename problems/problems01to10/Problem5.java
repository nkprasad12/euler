package problems.problems01to10;

import java.lang.invoke.MethodHandles;

import utils.primes.PrimeFactorization;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem5 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    PrimeFactorizations factor = new PrimeFactorizations(primes);

    PrimeFactorization allFactors = factor.of(1);
    for (long i = 2; i <= 20; i++) {
      allFactors = 
          allFactors.multiplyBy(
              allFactors.divideBy(factor.of(i)).reduce().denominator());
    }
    System.out.println(allFactors.toLong());
  }
}