package src.problems.problems001to010;

import java.lang.invoke.MethodHandles;
import src.utils.primes.PrimeFactorization;
import src.utils.primes.Primes;

public class Problem3 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    System.out.println(PrimeFactorization.of(600851475143l, new Primes()).largestFactor());
  }
}
