package problems.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorization;
import utils.primes.Primes;

public class Problem3 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    System.out.println(PrimeFactorization.of(600851475143l, new Primes()).largestFactor());
  }
}
